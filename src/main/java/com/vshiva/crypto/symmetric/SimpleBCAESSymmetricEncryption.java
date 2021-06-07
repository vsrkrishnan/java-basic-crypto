package com.vshiva.crypto.symmetric;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.crypto.CipherOutputStream;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.SymmetricSecretKey;
import org.bouncycastle.crypto.fips.FipsAES;
import org.bouncycastle.crypto.fips.FipsDRBG;
import org.bouncycastle.crypto.fips.FipsInputDecryptor;
import org.bouncycastle.crypto.fips.FipsOutputEncryptor;
import org.bouncycastle.crypto.fips.FipsSymmetricKeyGenerator;
import org.bouncycastle.crypto.fips.FipsSymmetricOperatorFactory;
import org.bouncycastle.crypto.util.BasicEntropySourceProvider;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

/**
 * @author sviswanathan
 * Sep 28, 2020
 */
public class SimpleBCAESSymmetricEncryption
{

    public static SecretKey createAESKey() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        //FipsSymmetricKeyGenerator<SymmetricSecretKey> keyGenerator = new FipsAES.KeyGenerator(256, secureRandom);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(SymmetricEncryptionUtils.AES, "BCFIPS");
        keyGenerator.init(256, secureRandom);
        return keyGenerator.generateKey();
    }

    public static byte[] createInitVector() {
        byte[] initVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initVector);
        return initVector;
    }

    public static byte[] performAESEncryption(String plainText, SecretKey secretKey, byte[] initVector) throws Exception {
        Cipher cipher = Cipher.getInstance(SymmetricEncryptionUtils.AES_CIPHER_ALGORITHM, "BCFIPS");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String performAESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initVector) throws Exception {
        Cipher cipher = Cipher.getInstance(SymmetricEncryptionUtils.AES_CIPHER_ALGORITHM, "BCFIPS");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }

    public static boolean performBCCryptoRoutine() throws Exception {
        // ensure a FIPS DRBG in use.
        CryptoServicesRegistrar.setSecureRandom(
                FipsDRBG.SHA512_HMAC.fromEntropySource(
                        new BasicEntropySourceProvider(new SecureRandom(), true))
                        .build(null, true));
        byte[] iv = new byte[16];
        CryptoServicesRegistrar.getSecureRandom().nextBytes(iv);
        FipsSymmetricKeyGenerator<SymmetricSecretKey> keyGen =
                new FipsAES.KeyGenerator(128,
                        CryptoServicesRegistrar.getSecureRandom());
        SymmetricSecretKey key = keyGen.generateKey();
        FipsSymmetricOperatorFactory<FipsAES.Parameters> fipsSymmetricFactory =
                new FipsAES.OperatorFactory();
        FipsOutputEncryptor<FipsAES.Parameters> outputEncryptor =
                fipsSymmetricFactory.createOutputEncryptor(key,
                        FipsAES.CBCwithPKCS7.withIV(iv));

        byte[] output = encryptBytes(outputEncryptor, new byte[16]);
        System.out.println(Arrays.toString(output));
        FipsInputDecryptor<FipsAES.Parameters> inputDecryptor =
                fipsSymmetricFactory.createInputDecryptor(key,
                        FipsAES.CBCwithPKCS7.withIV(iv));
        byte[] plain = decryptBytes(inputDecryptor, output);
        System.out.println(Arrays.toString(plain));
        return Arrays.equals(plain, output);
    }

    private static byte[] encryptBytes(
            FipsOutputEncryptor outputEncryptor, byte[] plainText) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        CipherOutputStream encOut = outputEncryptor.getEncryptingStream(bOut);
        encOut.update(plainText);
        encOut.close();
        return bOut.toByteArray();
    }

    private static byte[] decryptBytes(FipsInputDecryptor inputDecryptor,
            byte[] cipherText) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        InputStream encIn = inputDecryptor.getDecryptingStream(
                new ByteArrayInputStream(cipherText));
        int ch;
        while ((ch = encIn.read()) >= 0)
        {
            bOut.write(ch);
        }
        return bOut.toByteArray();
    }
}

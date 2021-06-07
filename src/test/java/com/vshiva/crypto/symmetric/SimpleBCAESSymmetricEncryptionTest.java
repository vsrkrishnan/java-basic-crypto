package com.vshiva.crypto.symmetric;

import static org.junit.jupiter.api.Assertions.*;

import java.security.Provider;
import java.security.Security;

import javax.crypto.SecretKey;

import org.bouncycastle.crypto.SymmetricSecretKey;
import org.junit.jupiter.api.Test;

/**
 * @author sviswanathan
 * Sep 28, 2020
 */
public class SimpleBCAESSymmetricEncryptionTest
{
    @Test
    public void testBCAESCryptoRoutine() throws Exception {
        //SimpleBCAESSymmetricEncryption.addBCProvider("C:DEFRND[CTRAES256];ENABLE{ALL};");
        for (Provider provider : Security.getProviders() ) {
            System.out.println(provider.getName());
        }
        SecretKey key = SimpleBCAESSymmetricEncryption.createAESKey();
        byte[] initVector = SimpleBCAESSymmetricEncryption.createInitVector();
        String plainText = "admin123A!!";
        byte[] cipherText = SimpleBCAESSymmetricEncryption.performAESEncryption(plainText, key, initVector);
        assertNotNull(cipherText);
        System.out.println(cipherText.toString());
        String decryptedText = SimpleBCAESSymmetricEncryption.performAESDecryption(cipherText, key, initVector);
        assertEquals(plainText, decryptedText);
    }

    @Test
    public void testBCFipsCryptoRoutine() throws Exception {
        assertTrue(SimpleBCAESSymmetricEncryption.performBCCryptoRoutine());
    }
}

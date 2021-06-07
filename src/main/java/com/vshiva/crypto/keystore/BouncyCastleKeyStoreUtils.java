package com.vshiva.crypto.keystore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;

import javax.crypto.SecretKey;

/**
 * @author sviswanathan
 * Oct 05, 2020
 */
public class BouncyCastleKeyStoreUtils
{
    private static final String SECRET_KEY_KEYSTORE_TYPE = "BCFKS";
    private static ByteArrayOutputStream bOut;

    public static void createSecretKeyJavaKeystore(String keyStorePassword, String keyAlias, SecretKey secretKey, String secretKeyPassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(SECRET_KEY_KEYSTORE_TYPE, "BCFIPS");
        keyStore.load(null, keyStorePassword.toCharArray());

        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
        KeyStore.SecretKeyEntry keyEntry = new KeyStore.SecretKeyEntry(secretKey);
        keyStore.setEntry(keyAlias, keyEntry, entryPassword);

        bOut = new ByteArrayOutputStream();
        keyStore.store(bOut, keyStorePassword.toCharArray());
    }

    public static KeyStore loadJavaKeyStore(String keyStorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(SECRET_KEY_KEYSTORE_TYPE, "BCFIPS");
        keyStore.load(new ByteArrayInputStream(bOut.toByteArray()), keyStorePassword.toCharArray());
        return keyStore;
    }

    public static SecretKey getSecretKeyFromKeystore(String keyStorePassword, String alias, String secretKeyPassword) throws Exception {
        KeyStore keyStore = loadJavaKeyStore(keyStorePassword);
        KeyStore.ProtectionParameter entryPassword= new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
        KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(alias, entryPassword);
        return keyEntry.getSecretKey();
    }
}

package com.vshiva.crypto.keystore;

import java.security.KeyStore;

import javax.crypto.SecretKey;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class KeyStoreUtils
{
    private static final String SECRET_KEY_KEYSTORE_TYPE = "BCFKS";

    public static KeyStore createPrivateKeyJavaKeyStore(String keystorePassword, String keyAlias, SecretKey secretKey, String secretKeyPassword) throws Exception{
        KeyStore keyStore = KeyStore.getInstance(SECRET_KEY_KEYSTORE_TYPE, "BCFIPS");
        keyStore.load(null, keystorePassword.toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
        KeyStore.SecretKeyEntry privateKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        keyStore.setEntry(keyAlias, privateKeyEntry, entryPassword);
        return keyStore;
    }
}

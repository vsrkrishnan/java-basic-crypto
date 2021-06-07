package com.vshiva.crypto.keystore;

import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import com.vshiva.crypto.symmetric.SymmetricEncryptionUtils;

/**
 * @author sviswanathan
 * Oct 05, 2020
 */
class BouncyCastleKeyStoreUtilsTest
{
    @Test
    void testJavaKeyStoreRoutine() throws Exception{
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(encodedSecretKey);
        BouncyCastleKeyStoreUtils.createSecretKeyJavaKeystore("password", "foo", secretKey, "keyPassword");
        KeyStore keyStore = BouncyCastleKeyStoreUtils.loadJavaKeyStore("password");
        assertNotNull(keyStore);

        SecretKey storedKey = BouncyCastleKeyStoreUtils.getSecretKeyFromKeystore("password", "foo", "keyPassword");
        assertNotNull(storedKey);
        String encodedStoredKey = Base64.getEncoder().encodeToString(storedKey.getEncoded());
        System.out.println(encodedStoredKey);
        assertEquals(encodedSecretKey, encodedStoredKey);
    }
}

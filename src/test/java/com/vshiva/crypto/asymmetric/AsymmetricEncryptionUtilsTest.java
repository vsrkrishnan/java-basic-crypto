package com.vshiva.crypto.asymmetric;

import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyPair;

import org.junit.Test;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class AsymmetricEncryptionUtilsTest
{
    @Test
    public void generateRSAKeyPair() throws Exception {
        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("Private Key: " + keyPair.getPrivate().toString());
        System.out.println("Public Key: " + keyPair.getPublic().toString());
    }

    @Test
    public void testRSACryptoRoutine() throws Exception {
        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        String plainText = "admin123A!!";
        byte[] cipherText = AsymmetricEncryptionUtils.performRSAEncryption(plainText, keyPair.getPrivate());
        assertNotNull(cipherText);
        String decryptedText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, keyPair.getPublic());
        assertEquals(plainText, decryptedText);
    }
}

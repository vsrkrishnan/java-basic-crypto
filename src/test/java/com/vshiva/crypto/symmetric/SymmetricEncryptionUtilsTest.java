package com.vshiva.crypto.symmetric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.crypto.SecretKey;

import org.junit.Test;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class SymmetricEncryptionUtilsTest
{
    @Test
    public void createAESKey() throws Exception {
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        assertNotNull(secretKey);
        System.out.println(secretKey.getEncoded().toString());
    }

    @Test
    public void testAESCryptoRoutine() throws Exception {
        SecretKey key = SymmetricEncryptionUtils.createAESKey();
        byte[] initVector = SymmetricEncryptionUtils.createInitVector();
        String plainText = "admin123A!!";
        byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(plainText, key, initVector);
        assertNotNull(cipherText);
        System.out.println(cipherText.toString());
        String decryptedText = SymmetricEncryptionUtils.performAESDecryption(cipherText, key, initVector);
        assertEquals(plainText, decryptedText);
    }
}

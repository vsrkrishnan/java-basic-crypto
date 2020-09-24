package com.vshiva.crypto.hash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class HashUtilsTest
{

    @Test
    public void generateRandomSalt() {
        byte[] salt = HashUtils.generateRandomSalt();
        assertNotNull(salt);
        System.out.println(salt.toString());
    }

    @Test
    public void createSHAHash() throws Exception {
        byte[] salt = HashUtils.generateRandomSalt();
        String valueToHash = UUID.randomUUID().toString();
        byte[] hash = HashUtils.createSHAHash(valueToHash, salt);
        assertNotNull(hash);
        System.out.println(Hex.toHexString(hash));

        byte[] hash2 = HashUtils.createSHAHash(valueToHash, salt);
        assertEquals(Hex.toHexString(hash), Hex.toHexString(hash2));
    }

    @Test
    public void testPasswordHashRoutine() {
        String secretPhrase = "admin123A!!";
        String passwordHash = HashUtils.hashPassword(secretPhrase);
        System.out.println(passwordHash);
        assertTrue(HashUtils.verifyPassword(secretPhrase, passwordHash));
    }
}

package com.vshiva.crypto.hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class HashUtils
{
    private static final String SHA_ALGORITHM = "SHA-512";

    public static byte[] generateRandomSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static byte[] createSHAHash(String input, byte[] salt) throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byteStream.write(salt);
        byteStream.write(input.getBytes());
        byte[] valueToHash = byteStream.toByteArray();

        MessageDigest messageDigest = MessageDigest.getInstance(SHA_ALGORITHM);
        return messageDigest.digest(valueToHash);
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(20));
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

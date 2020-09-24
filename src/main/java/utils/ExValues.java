package utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

/**
 * @author sviswanathan
 * Sep 21, 2020
 */
public class ExValues
{
    public static final long THIRTY_DAYS = 1000L * 60 * 60 * 24 * 30;
    public static final SecretKey SampleAesKey =
            new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f"), "AES");
    public static final SecretKey SampleTripleDesKey =
            new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f1011121314151617"), "TripleDES");
    public static final SecretKey SampleHMacKey =
            new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f10111213"), "HmacSHA512");
    public static final byte[] SampleInput = "Hello World!".getBytes();
    public static final byte[] SampleTwoBlockInput
            = "Some cipher modes require more than one block".getBytes();
    public static final byte[] Nonce = "number only used once".getBytes();
    public static final byte[] PersonalizationString
            = "a constant personal marker".getBytes();
    public static final byte[] Initiator = "Initiator".getBytes();
    public static final byte[] Recipient = "Recipient".getBytes();
    public static final byte[] UKM = "User keying material".getBytes();
}

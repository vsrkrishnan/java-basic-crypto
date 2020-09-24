package com.vshiva.crypto.signature;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class DigitalSignatureUtils
{
    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    public static byte[] createDigitalSignature(byte[] input, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(input);
        return signature.sign();
    }

    public static boolean verifyDigitalSignature(byte[] input, byte[] signatureToVerify, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }
}

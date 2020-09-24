package com.vshiva.crypto.signature;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import com.vshiva.crypto.asymmetric.AsymmetricEncryptionUtils;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class DigitalSignatureUtilsTest
{

    @Test
    public void digitalSignatureRoutine() throws Exception {
        URL uri = this.getClass().getClassLoader().getResource("demo.txt");
        Path path = Paths.get(uri.toURI());
        byte[] input = Files.readAllBytes(path);

        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
        System.out.println(Hex.toHexString(signature));

        assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
    }

}

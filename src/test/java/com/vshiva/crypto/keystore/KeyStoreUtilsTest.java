package com.vshiva.crypto.keystore;

import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyStore;

import javax.crypto.SecretKey;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import com.vshiva.crypto.symmetric.SymmetricEncryptionUtils;

/**
 * @author sviswanathan
 * Sep 23, 2020
 */
public class KeyStoreUtilsTest
{

    @Test
    public void createPrivateKeyJavaKeyStore() throws Exception{
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        String secretKeyHex = Hex.toHexString(secretKey.getEncoded());
        KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore("password", "foo", secretKey, "keyPassword");
        assertNotNull(keyStore);

        keyStore.load(null, "password".toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
        KeyStore.SecretKeyEntry resultEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("foo", entryPassword);
        SecretKey result = resultEntry.getSecretKey();
        String resultKeyHex = Hex.toHexString(result.getEncoded());
        assertEquals(secretKeyHex, resultKeyHex);
    }
}

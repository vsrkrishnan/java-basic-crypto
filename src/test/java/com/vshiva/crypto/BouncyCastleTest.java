package com.vshiva.crypto;//package src.main.java;

import javax.crypto.Cipher;

/**
 * @author sviswanathan
 * Sep 21, 2020
 */
public class BouncyCastleTest
{
    public static void main(String[] args) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS7Padding", "BCFIPS");
        System.out.println("Hello from Bouncy Castle!");
    }
}

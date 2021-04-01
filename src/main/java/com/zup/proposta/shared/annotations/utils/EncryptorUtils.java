package com.zup.proposta.shared.annotations.utils;


import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;


public class EncryptorUtils {

    public static final String password = "1a2b3c4d5e";

    public static final String salt = "0f1d2c3b";

    public static String encryptorDocument(String document) {
        return createTextEncryptor().encrypt(document);
    }

    public static String decryptingDocument(String encrypt) {
        return createTextEncryptor().decrypt(encrypt);
    }

    private static TextEncryptor createTextEncryptor() {
        return Encryptors.queryableText(password, salt);
    }
}

package com.applisto.appcloner.classes.util;

import android.util.Base64;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class SimpleCrypt {
    private String mKey;

    public SimpleCrypt(String key) {
        this.mKey = key;
    }

    public String encrypt(String plainText) {
        try {
            Cipher cipher = getCipher(1);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encryptedBytes, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(byte[] plainBytes) {
        try {
            Cipher cipher = getCipher(1);
            return cipher.doFinal(plainBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String encryptedText) throws IOException {
        try {
            Cipher cipher = getCipher(2);
            byte[] plainBytes = cipher.doFinal(Base64.decode(encryptedText, 0));
            return new String(plainBytes);
        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }

    public byte[] decrypt(byte[] encryptedBytes) throws IOException {
        try {
            Cipher cipher = getCipher(2);
            return cipher.doFinal(encryptedBytes);
        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }

    private Cipher getCipher(int cipherMode) throws Exception {
        SecretKeySpec keySpecification = new SecretKeySpec(this.mKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(cipherMode, keySpecification);
        return cipher;
    }
}

package com.cecilsoftwares.reussoftbackend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Plamedi L. Lusembo
 */
public class IdGenerator {

    private static IdGenerator uniqueInstance;

    public IdGenerator() {
    }

    public static synchronized IdGenerator getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new IdGenerator();
        }
        return uniqueInstance;
    }

    public static String generateId() throws Exception {
        Date today;
        String output;
        SimpleDateFormat formatter;
        String pattern = "dd/MM/yy-HH:mm:ss:SSSXXX | hh 'o''clock' a, zzzz";
        String alphabet = "_-=+%#$";

        formatter = new SimpleDateFormat(pattern, Locale.CANADA);
        today = new Date();
        output = formatter.format(today);

        char ins = alphabet.charAt(new Random().nextInt(alphabet.length()));
        int generatedInt = 1 + (int) (new Random().nextFloat() * (10 - 1));

        String idGen = String.valueOf(encrypt(output)) + cripto(output);

        return new StringBuilder(idGen).insert(idGen.length() - generatedInt, ins).toString();
    }

    private static byte[] encrypt(String input) throws Exception {

        String IV = "AAAAAAAAAAAAAAAA";
        String encryptKey = "0123456789abcdef";

        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(input.getBytes("UTF-8"));
    }

    private static String cripto(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(input.getBytes());
        byte[] hash = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length / 3; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

}

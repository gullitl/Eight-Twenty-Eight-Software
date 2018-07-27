package com.cecilsoftwares.reussoftbackend.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * @author Plamedi L. Lusembo
 */
public class IdGenerator {

    private static IdGenerator uniqueInstance;
    private static final Random RANDOM = new Random();

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
        String pattern = "ddMMyyHHmmssSSS";
        String alphabet = "_-=+%#$";

        formatter = new SimpleDateFormat(pattern, Locale.CANADA);
        today = new Date();
        output = formatter.format(today);

        char ins = alphabet.charAt(new Random().nextInt(alphabet.length()));
        int generatedInt = 1 + (int) (new Random().nextFloat() * (10 - 1));

        String idGen = output;

        return new StringBuilder(idGen).insert(idGen.length() - generatedInt, ins).toString();

    }

//    private static byte[] encrypt(String input) throws Exception {
//
//        String IV = "AAAAAAAAAAAAAAAA";
//        String encryptKey = "0123456789abcdef";
//
//        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"), "AES");
//        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
//        return encripta.doFinal(input.getBytes("UTF-8"));
//    }
//    private static String cripto(String input) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-1");
//        md.update(input.getBytes());
//        byte[] hash = md.digest();
//        StringBuffer hexString = new StringBuffer();
//        for (int i = 0; i < hash.length / 3; i++) {
//            if ((0xff & hash[i]) < 0x10) {
//                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
//            } else {
//                hexString.append(Integer.toHexString(0xFF & hash[i]));
//            }
//        }
//        return hexString.toString();
//    }
    public static String random(int count) {
        return random(count, false, false);
    }

    public static String randomAscii(int count) {
        return random(count, 32, 127, false, false);
    }

    public static String randomAlphabetic(int count) {
        return random(count, true, false);
    }

    public static String randomAlphanumeric(int count) {
        return random(count, true, true);
    }

    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
        return random(count, start, end, letters, numbers, chars, RANDOM);
    }

    public static String random(int count, String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, chars.toCharArray());
    }

    public static String random(int count, char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random) {
        if (count == 0) {
            return "";
        }
        if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if ((start == 0) && (end == 0)) {
            end = 123;
            start = 32;
            if ((!letters) && (!numbers)) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        char[] buffer = new char[count];
        int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[(random.nextInt(gap) + start)];
            }
            if (((letters) && (Character.isLetter(ch))) || ((numbers) && (Character.isDigit(ch))) || ((!letters) && (!numbers))) {

                if ((ch >= 56320) && (ch <= 57343)) {
                    if (count == 0) {
                        count++;
                    } else {
                        buffer[count] = ch;
                        count--;
                        buffer[count] = ((char) (55296 + random.nextInt(128)));
                    }
                } else if ((ch >= 55296) && (ch <= 56191)) {
                    if (count == 0) {
                        count++;
                    } else {
                        buffer[count] = ((char) (56320 + random.nextInt(128)));
                        count--;
                        buffer[count] = ch;
                    }
                } else if ((ch >= 56192) && (ch <= 56319)) {
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer).replace(" ", "-");
    }

}

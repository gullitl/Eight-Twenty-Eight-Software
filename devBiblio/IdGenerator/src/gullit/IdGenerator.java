package gullit;

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

    private static final String IV = "AAAAAAAAAAAAAAAA";
    private static final String encryptKey = "0123456789abcdef";

    private static final Random RANDOM = new Random();

    public IdGenerator() {
    }

    public static String generateId() throws Exception {
        Date today;
        String output;
        SimpleDateFormat formatter;
        String pattern = "dd/MM/yy-HH:mm:ss:SSS";

        formatter = new SimpleDateFormat(pattern, Locale.CANADA);
        today = new Date();
        output = formatter.format(today);
        String idGen = randomAlphanumeric(2) + String.valueOf(encrypt(output)) + randomAlphabetic(2);
        idGen = idGen.substring(0, idGen.length() / 2) + randomAscii(2)
                + idGen.substring(idGen.length() / 2, idGen.length());
        return idGen;
    }

    private static byte[] encrypt(String textopuro) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }

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
        return new String(buffer);
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

}

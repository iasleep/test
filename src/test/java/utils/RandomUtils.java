package utils;

import java.util.Random;

public class RandomUtils {

    private final static Random random = new Random();
    private final static Integer MIN_STRING = 5;
    private final static Integer MAX_STRING = 9;

    private static String genRandomStringFromSymbols(int len, String allowedSymbols) {
        StringBuffer out = new StringBuffer();
        char[] symb = allowedSymbols.toCharArray();
        int allowedLen = allowedSymbols.length();

        out.append(Character.toUpperCase(symb[Math.abs(random.nextInt()) % allowedLen]));
        for (int i = 1; i < len; ++i) {
            out.append(symb[Math.abs(random.nextInt()) % allowedLen]);
        }

        return out.toString();
    }

    public static Integer genInt(int min, int max) {
        return min + Math.abs(random.nextInt()) % (max - min +1);
    }

    public static String genENString(int min, int max) {
        return genRandomStringFromSymbols(genInt(min, max),
                "abcdefghijklpnopqrstuvwxyzsdhfkhkqjwahsaisjcoixjdff");
    }

    public static String genENString() {
        return genENString(MIN_STRING, MAX_STRING);
    }
}

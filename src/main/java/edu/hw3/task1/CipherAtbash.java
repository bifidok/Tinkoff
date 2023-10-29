package edu.hw3.task1;

public class CipherAtbash {
    private static final int LATIN_ALPHABET_COUNT = 26;
    private static final int LATIN_ALPHABET_FIRST_LETTER_ASCII_POSITION_LOWERCASE = 65;
    private static final int LATIN_ALPHABET_LAST_LETTER_ASCII_POSITION_LOWERCASE = 90;
    private static final int LATIN_ALPHABET_FIRST_LETTER_ASCII_POSITION_UPPERCASE = 97;
    private static final int LATIN_ALPHABET_LAST_LETTER_ASCII_POSITION_UPPERCASE = 122;

    private CipherAtbash() {

    }

    public static String atbash(String toEncode) {
        char[] decoded = toEncode.toCharArray();
        for (int i = 0; i < decoded.length; i++) {
            if (isLatin(decoded[i])) {
                char mirrored;
                if (Character.isUpperCase(decoded[i])) {
                    mirrored = (char) (LATIN_ALPHABET_COUNT - (decoded[i] - 'A') - 1 + 'A');
                } else {
                    mirrored = (char) (LATIN_ALPHABET_COUNT - (decoded[i] - 'a') - 1 + 'a');
                }
                decoded[i] = mirrored;
            }
        }
        return String.valueOf(decoded);
    }

    private static boolean isLatin(char ch) {
        return (ch >= LATIN_ALPHABET_FIRST_LETTER_ASCII_POSITION_LOWERCASE
            && ch <= LATIN_ALPHABET_LAST_LETTER_ASCII_POSITION_LOWERCASE)
            || (ch >= LATIN_ALPHABET_FIRST_LETTER_ASCII_POSITION_UPPERCASE
            && ch <= LATIN_ALPHABET_LAST_LETTER_ASCII_POSITION_UPPERCASE);
    }
}

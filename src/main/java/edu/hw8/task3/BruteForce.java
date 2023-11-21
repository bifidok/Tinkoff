package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class BruteForce {
    private final static String HASH_TYPE = "MD5";
    private int passwordMaxLength;
    private Map<String, String> hackedDatabase;
    private Map<String, String> bruteForcedData;

    public BruteForce(int passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
    }

    @SuppressWarnings("MagicNumber")
    public Map<String, String> start(Path databaseFile) {
        DatabaseReader databaseReader = new DatabaseReader();
        PasswordGenerator generator = new PasswordGenerator(passwordMaxLength);
        generator.start();
        hackedDatabase = databaseReader.read(databaseFile);
        bruteForcedData = new HashMap<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_TYPE);
            while (!hackedDatabase.isEmpty()) {
                String generatedPassword = generator.nextPassword();
                messageDigest.update(generatedPassword.getBytes(StandardCharsets.UTF_8));
                byte[] digest = messageDigest.digest();
                for (int i = 0; i < digest.length; ++i) {
                    stringBuilder.append(Integer.toHexString((digest[i] & 0xFF) | 0x100), 1, 3);
                }
                if (hackedDatabase.containsKey(stringBuilder.toString())) {
                    bruteForcedData.put(hackedDatabase.get(stringBuilder.toString()), generatedPassword);
                    hackedDatabase.remove(stringBuilder.toString());
                }
                stringBuilder.delete(0, stringBuilder.length());
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return bruteForcedData;
    }
}

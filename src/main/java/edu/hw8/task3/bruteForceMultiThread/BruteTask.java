package edu.hw8.task3.bruteForceMultiThread;

import edu.hw8.task3.PasswordGenerator;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class BruteTask implements Runnable {
    private final Map<String, String> hackedDatabase;
    private final PasswordGenerator generator;
    private final Map<String, String> bruteForcedData;
    private String hashType;

    public BruteTask(
        Map<String, String> hackedDatabase,
        String hashType,
        PasswordGenerator generator,
        Map<String, String> bruteForcedData
    ) {
        this.hackedDatabase = hackedDatabase;
        this.hashType = hashType;
        this.generator = generator;
        this.bruteForcedData = bruteForcedData;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public void run() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
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
    }
}

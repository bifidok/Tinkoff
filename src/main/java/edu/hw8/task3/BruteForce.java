package edu.hw8.task3;

import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class BruteForce {
    private final String HASH_TYPE = "MD5";
    private Map<String,String> hackedDatabase;
    private Map<String,String> bruteForcedData;

    public void start(Path databaseFile){
        DatabaseReader databaseReader = new DatabaseReader();
        hackedDatabase = databaseReader.read(databaseFile);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_TYPE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
//        messageDigest.update(pass.getBytes(StandardCharsets.UTF_8));
//        byte [] diget = messageDigest.digest();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < diget.length; ++i) {
//            sb.append(Integer.toHexString((diget[i] & 0xFF) | 0x100).substring(1,3));
//        }
        while (hackedDatabase.size() > 0){

        }
    }
    private String nextPassword(){
        return null;
    }
}

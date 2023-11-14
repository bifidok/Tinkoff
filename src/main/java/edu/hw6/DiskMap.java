package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private String filePath;
    private Map<String, String> diskMap;

    public DiskMap(String filePath) {
        this.filePath = filePath;
        diskMap = load();
    }

    public void save() {
        File file = new File(filePath);
        List<String> entries = new ArrayList<>(diskMap.size());
        for (Map.Entry<String, String> entry : diskMap.entrySet()) {
            entries.add(entry.toString());
        }
        try {
            Files.write(file.toPath(), entries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> load() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Map<String, String> map = new HashMap<>();
        try {
            List<String> entries = new ArrayList<>(Files.readAllLines(file.toPath()));
            for (String entry : entries) {
                var keyToValue = entry.split("=");
                map.put(keyToValue[0], keyToValue[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public int size() {
        return diskMap.size();
    }

    @Override
    public boolean isEmpty() {
        return diskMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return diskMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return diskMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return diskMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return diskMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return diskMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        diskMap.putAll(m);
    }

    @Override
    public void clear() {
        diskMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return diskMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return diskMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return diskMap.entrySet();
    }
}

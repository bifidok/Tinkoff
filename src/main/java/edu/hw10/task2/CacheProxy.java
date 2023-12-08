package edu.hw10.task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Path defaultPath = Path.of("src/main/resources");
    private Map<String, Boolean> persistMethods;
    private Object target;
    private Map<String, Object> cache;

    private CacheProxy(Object target, Map<String, Boolean> persistMethods) {
        this.target = target;
        this.persistMethods = persistMethods;
        cache = new HashMap<>();
    }

    public static <T> T create(T target, Class targetClass) {
        ClassLoader loader = targetClass.getClassLoader();
        Class[] interfaces = targetClass.getInterfaces();
        Map<String, Boolean> persistMethods = new HashMap<>();
        for (var inter : interfaces) {
            for (var method : inter.getDeclaredMethods()) {
                Cache cache = (Cache) findAnnotation(method,Cache.class);
                if(cache != null){
                    persistMethods.put(method.getName(),cache.persist());
                }else{
                    persistMethods.put(method.getName(),false);
                }
            }
        }
        CacheProxy instance = new CacheProxy(target, persistMethods);
        return (T) Proxy.newProxyInstance(loader, interfaces, instance);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Boolean persist = persistMethods.get(method.getName());
        var result = method.invoke(target, args);
        if (persist != null && persist == true) {
            persist(method, result);
        }
        return result;
    }

    private void persist(Method method, Object result) throws IOException {
        String key = method.getName() + Arrays.toString(method.getParameters());
        cache.put(key, result);
        Path path = Path.of(defaultPath + "/cache/" + key + ".txt");
        if(!path.toFile().exists()){
            path.toFile().createNewFile();
        }
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            writer.writeObject(key + " " + result.toString());
        }
    }
    private static Annotation findAnnotation(Method method, Class aClass){
        Annotation result = null;
        for (var annotation : method.getDeclaredAnnotations()) {
            if (annotation.annotationType().equals(aClass)) {
                result = annotation;
                break;
            }
        }
        return result;
    }
}

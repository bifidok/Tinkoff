package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    private final static Random RANDOM = new Random();

    public Object nextObject(Class classToCreate)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor[] constructors = classToCreate.getConstructors();
        Constructor randomConstructor = constructors[RANDOM.nextInt(constructors.length)];
        Parameter[] parameters = randomConstructor.getParameters();
        if (parameters.length == 0) {
            return randomConstructor.newInstance();
        }
        Object[] randomizedArgs = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            randomizedArgs[i] = randomArgValue(parameters[i]);
        }
        return randomConstructor.newInstance(randomizedArgs);
    }

    public Object nextObject(Class classToCreate, String factoryMethod)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Method[] methods = classToCreate.getDeclaredMethods();
        Method method = Arrays.stream(methods)
            .filter(m -> m.getName().equals(factoryMethod))
            .findFirst()
            .orElseThrow(() -> new NoSuchMethodException(String.format(
                "Factory method: \"%s\" not found in class \"%s\"",
                factoryMethod,
                classToCreate
            )));
        Parameter[] parameters = method.getParameters();
        Object[] randomizedArgs = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            randomizedArgs[i] = randomArgValue(parameters[i]);
        }
        return method.invoke(null, randomizedArgs);
    }

    private Object randomArgValue(Parameter parameter) {
        Object min = null;
        Object max = null;
        if (parameter.isAnnotationPresent(Min.class)) {
            min = parameter.getAnnotation(Min.class).value();
        }
        if (parameter.isAnnotationPresent(Max.class)) {
            max = parameter.getAnnotation(Max.class).value();
        }
        return getRandomValue(parameter, min, max);
    }

    @SuppressWarnings("CyclomaticComplexity")
    private Object getRandomValue(Parameter parameter, Object min, Object max) {
        Class argClass = parameter.getType();
        Object randomValue = null;
        if (argClass == byte.class || argClass == Byte.class) {
            randomValue = RANDOM.nextInt((byte) getMin(min, Type.BYTE), (byte) getMax(max, Type.BYTE));
        } else if (argClass == short.class || argClass == Short.class) {
            randomValue = RANDOM.nextInt((short) getMin(min, Type.SHORT), (short) getMax(max, Type.SHORT));
        } else if (argClass == int.class || argClass == Integer.class) {
            randomValue = RANDOM.nextInt((int) getMin(min, Type.INT), (int) getMax(max, Type.INT));
        } else if (argClass == long.class || argClass == Long.class) {
            randomValue = RANDOM.nextLong((long) getMin(min, Type.LONG), (long) getMax(max, Type.LONG));
        } else if (argClass == float.class || argClass == Float.class) {
            randomValue = RANDOM.nextFloat((float) getMin(min, Type.FLOAT), (float) getMax(max, Type.FLOAT));
        } else if (argClass == double.class || argClass == Double.class) {
            randomValue = RANDOM.nextDouble((double) getMin(min, Type.DOUBLE), (double) getMax(max, Type.DOUBLE));
        } else if (argClass == boolean.class || argClass == Boolean.class) {
            randomValue = RANDOM.nextBoolean();
        } else if (argClass == char.class || argClass == Character.class) {
            randomValue = (char) (RANDOM.nextInt(Character.CURRENCY_SYMBOL) + 'a');
        } else if (argClass == String.class) {
            randomValue = UUID.randomUUID().toString();
        }
        return randomValue;
    }

    private Object getMin(Object min, Type type) {
        if (min != null) {
            return min;
        }
        return switch (type) {
            case LONG -> Long.MIN_VALUE;
            case INT -> Integer.MIN_VALUE;
            case SHORT -> Short.MIN_VALUE;
            case BYTE -> Byte.MIN_VALUE;
            case DOUBLE -> Double.MIN_VALUE;
            case FLOAT -> Float.MIN_VALUE;
            default -> 0;
        };
    }

    private Object getMax(Object max, Type type) {
        if (max != null) {
            return max;
        }
        return switch (type) {
            case LONG -> Long.MAX_VALUE;
            case INT -> Integer.MAX_VALUE;
            case SHORT -> Short.MAX_VALUE;
            case BYTE -> Byte.MAX_VALUE;
            case DOUBLE -> Double.MAX_VALUE;
            case FLOAT -> Float.MAX_VALUE;
            default -> 0;
        };
    }
}

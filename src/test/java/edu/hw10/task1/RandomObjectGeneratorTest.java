package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.testClasses.SomeClassNoAnnotations;
import edu.hw10.task1.testClasses.SomeClassWithAnnotations;
import edu.hw10.task1.testClasses.SomeRecord;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomObjectGeneratorTest {
    private static RandomObjectGenerator generator;

    @BeforeAll
    public static void init() {
        generator = new RandomObjectGenerator();
    }

    @Test
    @DisplayName("Генерация объекта через конструктор без аннотаций")
    public void nextObject_whenNoAnnotationsAndWithConstructor() {
        try {
            SomeClassNoAnnotations someClass =
                (SomeClassNoAnnotations) generator.nextObject(SomeClassNoAnnotations.class);

            assertThat(someClass).isNotNull();
            assertThat(someClass.getStr()).isNotNull();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Генерация объекта через фабричный метод без аннотаций")
    public void nextObject_whenNoAnnotationsAndWithFactoryMethod() {
        try {
            SomeClassNoAnnotations someClass =
                (SomeClassNoAnnotations) generator.nextObject(SomeClassNoAnnotations.class, "create");

            assertThat(someClass).isNotNull();
            assertThat(someClass.getStr()).isNotNull();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Генерация объекта через конструктор с аннотациями")
    public void nextObject_whenAnnotationsAndWithConstructor() {
        try {
            Constructor[] constructors = SomeClassWithAnnotations.class.getConstructors();
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            for (var constructor : constructors) {
                for (var annotation : constructor.getAnnotations()) {
                    if (annotation.annotationType().equals(Min.class)) {
                        Min ann = (Min) annotation;
                        min = ann.value();
                    }
                    if (annotation.annotationType().equals(Max.class)) {
                        Max ann = (Max) annotation;
                        max = ann.value();
                    }
                }
            }

            SomeClassWithAnnotations someClass =
                (SomeClassWithAnnotations) generator.nextObject(SomeClassWithAnnotations.class);

            assertThat(someClass).isNotNull();
            assertThat(someClass.getNum() >= min).isTrue();
            assertThat(someClass.getNum() <= max).isTrue();
            assertThat(someClass.getStr()).isNotNull();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Генерация объекта через фабричный метод с аннотациями")
    public void nextObject_whenAnnotationsAndWithFactoryMethod() {
        try {
            Method factoryMethod = SomeClassWithAnnotations.class.getDeclaredMethod("create", int.class);
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            for (var annotation : factoryMethod.getAnnotations()) {
                if (annotation.annotationType().equals(Min.class)) {
                    Min ann = (Min) annotation;
                    min = ann.value();
                }
                if (annotation.annotationType().equals(Max.class)) {
                    Max ann = (Max) annotation;
                    max = ann.value();
                }
            }

            SomeClassWithAnnotations someClass =
                (SomeClassWithAnnotations) generator.nextObject(SomeClassWithAnnotations.class);

            assertThat(someClass).isNotNull();
            assertThat(someClass.getNum() >= min).isTrue();
            assertThat(someClass.getNum() <= max).isTrue();
            assertThat(someClass.getStr()).isNotNull();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Генерация record")
    public void nextObject_whenRecord() {
        try {
            SomeRecord someRecord =
                (SomeRecord) generator.nextObject(SomeRecord.class);

            assertThat(someRecord).isNotNull();
            assertThat(someRecord.str()).isNotNull();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}

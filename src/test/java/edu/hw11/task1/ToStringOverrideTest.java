package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ToStringOverrideTest {

    @Test
    @DisplayName("Method toString should return string Hello, ByteBuddy!")
    public void toString_shouldOverrideMethod() throws InstantiationException, IllegalAccessException {
        String expectedString = "Hello, ByteBuddy!";
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(expectedString))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        Object overriden = dynamicType.newInstance();

        assertThat(overriden.toString()).isEqualTo(expectedString);
    }
}

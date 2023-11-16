package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Факториал")
    public void factorial(){
        int num = 1412;
        BigInteger factorialOfNum = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            factorialOfNum = factorialOfNum.multiply(BigInteger.valueOf(i));
        }

        BigInteger actual = Task2.factorial(num);

        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(factorialOfNum);
    }

    @Test
    @DisplayName("Факториал нуля")
    public void factorial_whenZero(){
        int num = 0;
        BigInteger factorialOfNum = BigInteger.ONE;

        BigInteger actual = Task2.factorial(num);

        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(factorialOfNum);
    }

    @Test
    @DisplayName("Факториал отрицательного числа")
    public void factorial_whenNegative(){
        int num = -10;

        BigInteger actual = Task2.factorial(num);

        assertThat(actual).isNull();
    }
}

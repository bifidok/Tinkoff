package edu.hw3.task1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CipherAtbashTest {
    @Test
    @DisplayName("Базовый тест")
    public void cipherAtbash_shouldCorrectlyEncode(){
        String toEncode = "Hello world!";
        String ans = "Svool dliow!";
        assertThat(CipherAtbash.atbash(toEncode)).isEqualTo(ans);
    }
    @Test
    @DisplayName("Строка из 1 не латинского символа")
    public void cipherAtbash_shouldCorrectlyEncode_whenStringLengthOne(){
        String toEncode = "!";
        String ans = "!";
        assertThat(CipherAtbash.atbash(toEncode)).isEqualTo(ans);
    }
    @Test
    @DisplayName("Пустая строка")
    public void cipherAtbash_shouldReturnEmptyString(){
        String toEncode = "";
        String ans = "";
        assertThat(CipherAtbash.atbash(toEncode)).isEqualTo(ans);
    }
}

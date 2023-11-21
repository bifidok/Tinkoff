package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTest {
    @Test
    @DisplayName("Проверка валидных паролей")
    public void validate_shouldMatchAllPasswords() {
        String[] passwords = new String[] {
            "adfafadfa!sdf",
            "a@011sdf",
            "$",
            "44r4!@#$!",
            "!@#$%^&*()_",
            "76546|||21412",
        };

        for (String password : passwords) {
            assertThat(PasswordValidator.validate(password)).isTrue();
        }
    }

    @Test
    @DisplayName("Проверка невалидных паролей")
    public void validate_shouldNotMatchAllPasswords() {
        String[] passwords = new String[] {
            "adfafadfasdf",
            "a011sdf",
            "",
            "44r4",
            "  ",
            "7654621412",
        };

        for (String password : passwords) {
            assertThat(PasswordValidator.validate(password)).isFalse();
        }
    }
}

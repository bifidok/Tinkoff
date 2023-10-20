package edu.hw3.task5;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContactParserTest {
    @Test
    @DisplayName("Базовый тест ASC")
    public void parseContacts_ShouldCorrectlyParseASC() {
        var input = List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");
        var answer = List.of(new Person("Thomas", "Aquinas")
            , new Person("Rene", "Descartes")
            , new Person("David", "Hume")
            , new Person("John", "Locke"));

        var result = ContactParser.parseContacts(input, "ASC");

        assertThat(answer.size()).isEqualTo(result.size());
        for (int i = 0; i < answer.size(); i++) {
            assertThat(answer.get(i).surname()).isEqualTo(result.get(i).surname());
        }
    }

    @Test
    @DisplayName("У некоторых нет фамилий DESC")
    public void parseContacts_ShouldCorrectlyParseDESC_whenSomeoneDoesntHaveSurname() {
        var input = List.of("John", "Thomas Aquinas", "David", "Rene Bescartes");
        var answer = List.of(new Person("John", "")
            , new Person("David", "")
            , new Person("Rene", "Bescartes")
            , new Person("Thomas", "Aquinas"));

        var result = ContactParser.parseContacts(input, "DESC");

        assertThat(answer.size()).isEqualTo(result.size());
        for (int i = 0; i < answer.size(); i++) {
            assertThat(answer.get(i).surname()).isEqualTo(result.get(i).surname());
        }
    }
}

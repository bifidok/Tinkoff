package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.StudentDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentDatabaseTest {
    @Test
    @DisplayName("Проверка корректной записи")
    public void add_shouldWriteFromAllThreads() {
        PersonDatabase database = new StudentDatabase();
        Thread thread = new Thread(() -> {
            database.add(new Person(1, "Bob", "Sadovaya 3", "+7875962235"));
            database.add(new Person(2, "Kevin", "Sadovaya 14", "+8765434567"));
        });
        thread.start();
        database.add(new Person(3, "Marlin", "Sadovaya 15", "+9876787444"));
        database.add(new Person(4, "Krosh", null, "+9874187444"));

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(database.findByName("Bob")).isNotNull();
        assertThat(database.findByName("Kevin")).isNotNull();
        assertThat(database.findByName("Marlin")).isNotNull();

        assertThat(database.findByAddress("Sadovaya 3")).isNotNull();
        assertThat(database.findByAddress("Sadovaya 14")).isNotNull();
        assertThat(database.findByAddress("Sadovaya 15")).isNotNull();

        assertThat(database.findByPhone("+7875962235")).isNotNull();
        assertThat(database.findByPhone("+8765434567")).isNotNull();
        assertThat(database.findByPhone("+9876787444")).isNotNull();
    }

    @Test
    @DisplayName("Проверка условия нахождения человека")
    public void find_shouldNotFindPerson() {
        PersonDatabase database = new StudentDatabase();
        database.add(new Person(4, "Krosh", null, "+9874187444"));

        assertThat(database.findByName("Krosh")).isNull();
        assertThat(database.findByPhone("+9874187444")).isNull();
    }
}

package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class StudentDatabase implements PersonDatabase {
    private final Map<Integer, Person> students;

    public StudentDatabase() {
        students = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        if (person != null) {
            students.put(person.id(), person);
        }
    }

    @Override
    public synchronized void delete(int id) {
        students.remove(id);
    }

    @Override
    public @Nullable Person findByName(String name) {
        Optional<Person> student = students
            .values()
            .stream()
            .filter(person -> person.name() != null && person.name().equals(name))
            .findAny();
        if (student.isPresent()) {
            if (student.get().address() == null || student.get().phoneNumber() == null) {
                return null;
            }
        }
        return student.orElse(null);
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        Optional<Person> student = students
            .values()
            .stream()
            .filter(person -> person.address() != null && person.address().equals(address))
            .findAny();
        if (student.isPresent()) {
            if (student.get().name() == null || student.get().phoneNumber() == null) {
                return null;
            }
        }
        return student.orElse(null);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        Optional<Person> student = students
            .values()
            .stream()
            .filter(person -> person.phoneNumber() != null && person.phoneNumber().equals(phone))
            .findAny();
        if (student.isPresent()) {
            if (student.get().name() == null || student.get().address() == null) {
                return null;
            }
        }
        return student.orElse(null);
    }
}

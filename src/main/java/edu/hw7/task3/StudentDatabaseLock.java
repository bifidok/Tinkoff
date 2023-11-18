package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class StudentDatabaseLock implements PersonDatabase {
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(true);

    private final Map<Integer, Person> students;

    public StudentDatabaseLock() {
        students = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        LOCK.readLock().lock();
        try {
            if (person != null) {
                students.put(person.id(), person);
            }
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        LOCK.readLock().lock();
        try {
            students.remove(id);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        LOCK.writeLock().lock();
        Optional<Person> student;
        try {
            student = students
                .values()
                .stream()
                .filter(person -> person.name() != null && person.name().equals(name))
                .findAny();
            if (student.isPresent()) {
                if (student.get().address() == null || student.get().phoneNumber() == null) {
                    student = Optional.empty();
                }
            }
        } finally {
            LOCK.writeLock().unlock();
        }
        return student.orElse(null);
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        LOCK.writeLock().lock();
        Optional<Person> student;
        try {
            student = students
                .values()
                .stream()
                .filter(person -> person.address() != null && person.address().equals(address))
                .findAny();
            if (student.isPresent()) {
                if (student.get().name() == null || student.get().phoneNumber() == null) {
                    student = Optional.empty();
                }
            }
        } finally {
            LOCK.writeLock().unlock();
        }
        return student.orElse(null);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        LOCK.writeLock().lock();
        Optional<Person> student;
        try {
            student = students
                .values()
                .stream()
                .filter(person -> person.phoneNumber() != null && person.phoneNumber().equals(phone))
                .findAny();
            if (student.isPresent()) {
                if (student.get().name() == null || student.get().address() == null) {
                    student = Optional.empty();
                }
            }
        } finally {
            LOCK.writeLock().unlock();
        }
        return student.orElse(null);
    }
}

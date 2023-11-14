package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalValidator {
    private static final String TYPE_ERROR_MESSAGE = "type cant be null";
    private static final String SEX_ERROR_MESSAGE = "sex cant be null";
    private static final String NUM_ERROR_MESSAGE = "nums cant be less than 0";

    private AnimalValidator() {
    }

    public static Map<String, ValidationError> validateTypeProperty(List<Animal> animals) {
        return animals.stream()
            .filter(o -> o.type() == null)
            .collect(Collectors
                .toMap(Animal::name, a -> new ValidationError("type", TYPE_ERROR_MESSAGE)));
    }

    public static Map<String, ValidationError> validateSexProperty(List<Animal> animals) {
        return animals.stream()
            .filter(o -> o.sex() == null)
            .collect(Collectors
                .toMap(Animal::name, a -> new ValidationError("sex", SEX_ERROR_MESSAGE)));
    }

    public static Map<String, ValidationError> validateNumericProperties(List<Animal> animals) {
        return animals.stream()
            .filter(o -> o.age() < 0 || o.weight() < 0 || o.height() < 0)
            .collect(Collectors
                .toMap(Animal::name, a -> new ValidationError("age/weight/height", NUM_ERROR_MESSAGE)));
    }
}

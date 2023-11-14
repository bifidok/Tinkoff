package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalRepository {
    private final static int HUNDRED_CENTIMETERS = 100;

    private AnimalRepository() {

    }

    public static List<Animal> sortByHeight(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> firstKSortedByWeight(List<Animal> list, int k) {
        return list.stream()
            .sorted(((animal1, animal2) -> animal2.weight() - animal1.weight()))
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> getTypesCount(List<Animal> list) {
        return list.stream()
            .collect(Collectors.toMap(Animal::type, animal -> 1, Math::addExact));
    }

    public static Animal getByLongestName(List<Animal> list) {
        return list.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getGreaterSex(List<Animal> list) {
        return list.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey).orElse(null);
    }

    public static Map<Animal.Type, Animal> getHeavierAnimalForEachType(List<Animal> list) {
        return list.stream()
            .collect(Collectors.toMap(Animal::type, animal -> animal,
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal getKOldest(List<Animal> list, int k) {
        var sortedList = list.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .limit(k + 1)
            .toList();
        return k > sortedList.size() ? null : sortedList.get(k - 1);
    }

    public static Optional<Animal> getHeavierBelowK(List<Animal> list, int k) {
        return list.stream()
            .filter(animal -> animal.weight() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countAllPaws(List<Animal> list) {
        return list.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAllWhereAgeIsNotEqualToPawsCount(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> getAllWhichBitesAndHigherHundredCM(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.bites())
            .filter(animal -> animal.height() > HUNDRED_CENTIMETERS)
            .collect(Collectors.toList());
    }

    public static Integer countAnimalsWhereWeightLargerThanHeight(List<Animal> list) {
        return Math.toIntExact(list.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    public static List<Animal> getAllWhereNameHasMoreThanTwoWords(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static boolean containsDogHeightLargerThanK(List<Animal> list, int k) {
        return list.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG))
            .anyMatch(animal -> animal.height() > k);
    }

    public static Integer countWeightSumByAge(List<Animal> list, int ageFrom, int ageTo) {
        return list.stream()
            .filter(animal -> animal.age() >= ageFrom && animal.age() <= ageTo)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortByTypeThenSexThenName(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean isSpiderBitesOftenThanDog(List<Animal> list) {
        long spiderBitesCount = list.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER))
            .filter(Animal::bites)
            .count();
        long dogBitesCount = list.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG))
            .filter(Animal::bites)
            .count();
        return spiderBitesCount > dogBitesCount;
    }

    public static Animal getHeavierFish(List<Animal>... list) {
        return Arrays.stream(list)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getAnimalsWithBadRecords(List<Animal> list) {
        Map<String, ValidationError> typeFieldErrors = AnimalValidator.validateTypeProperty(list);
        Map<String, ValidationError> sexFieldErrors = AnimalValidator.validateSexProperty(list);
        Map<String, ValidationError> numberVariablesFieldErrors = AnimalValidator.validateNumericProperties(list);

        return Stream.of(typeFieldErrors, sexFieldErrors, numberVariablesFieldErrors)
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.groupingBy(
                entry -> entry.getKey(),
                Collectors.mapping(entry -> entry.getValue(), Collectors.toSet())
            ));
    }

    public static Map<String, String> getAnimalsWithBadRecordsBetter(List<Animal> list) {
        Map<String, ValidationError> typeFieldErrors = AnimalValidator.validateTypeProperty(list);
        Map<String, ValidationError> sexFieldErrors = AnimalValidator.validateSexProperty(list);
        Map<String, ValidationError> numberVariablesFieldErrors = AnimalValidator.validateNumericProperties(list);

        return Stream.of(typeFieldErrors, sexFieldErrors, numberVariablesFieldErrors)
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.groupingBy(
                entry -> entry.getKey(),
                Collectors.mapping(e -> e.getValue().property(), Collectors.joining(", "))
            ));
    }

}

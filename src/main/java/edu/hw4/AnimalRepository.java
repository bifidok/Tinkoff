package edu.hw4;

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
            .sorted((o1, o2) -> o1.height() - o2.height())
            .collect(Collectors.toList());
    }

    public static List<Animal> firstKSortedByWeight(List<Animal> list, int k) {
        return list.stream()
            .sorted(((o1, o2) -> o2.weight() - o1.weight()))
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> getTypesCount(List<Animal> list) {
        return list.stream()
            .collect(Collectors.toMap(Animal::type, a -> 1, Math::addExact));
    }

    public static Animal getByLongestName(List<Animal> list) {
        return list.stream()
            .max((o1, o2) -> o1.name().length() - o2.name().length()).orElse(null);
    }

    public static Animal.Sex getGreaterSex(List<Animal> list) {
        return list.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max((o1, o2) -> (int) (o1.getValue() - o2.getValue())).get().getKey();
    }

    public static Map<Animal.Type, Animal> getHeavierAnimalForEachType(List<Animal> list) {
        return list.stream()
            .collect(Collectors.toMap(Animal::type, a -> a,
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal getKOldest(List<Animal> list, int k) {
        var sortedList = list.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .limit(k + 1).toList();
        return sortedList.get(k - 1);
    }

    public static Optional<Animal> getHeavierBelowK(List<Animal> list, int k) {
        return list.stream()
            .filter(o -> o.weight() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countAllPaws(List<Animal> list) {
        return list.stream()
            .mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getAllWhereAgeIsNotEqualToPawsCount(List<Animal> list) {
        return list.stream()
            .filter(o -> o.age() != o.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> getAllWhichBitesAndHigherHundredCM(List<Animal> list) {
        return list.stream()
            .filter(o -> o.bites())
            .filter(o -> o.height() > HUNDRED_CENTIMETERS).collect(Collectors.toList());
    }

    public static Integer countAnimalsWhereWeightLargerThanHeight(List<Animal> list) {
        return Math.toIntExact(list.stream()
            .filter(o -> o.weight() > o.height())
            .count());
    }

    public static List<Animal> getAllWhereNameHasMoreThanTwoWords(List<Animal> list) {
        return list.stream()
            .filter(o -> o.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static boolean containsDogHeightLargerThanK(List<Animal> list, int k) {
        return list.stream()
            .filter(o -> o.type().equals(Animal.Type.DOG))
            .anyMatch(o -> o.height() > k);
    }

    public static Integer countWeightSumByAge(List<Animal> list, int ageFrom, int ageTo) {
        return list.stream()
            .filter(o -> o.age() >= ageFrom && o.age() <= ageTo)
            .mapToInt(Animal::weight).sum();
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
            .filter(s -> s.type().equals(Animal.Type.SPIDER))
            .filter(Animal::bites).count();
        long dogBitesCount = list.stream()
            .filter(d -> d.type().equals(Animal.Type.DOG))
            .filter(Animal::bites).count();
        return spiderBitesCount > dogBitesCount;
    }

    public static Animal getHeavierFish(List<Animal>... list) {
        Animal heavierFish = null;
        int maxWeight = -1;

        for (List<Animal> subList : list) {
            Animal heavierFishInSubList = subList.stream()
                .filter(o -> o.type().equals(Animal.Type.FISH))
                .max(Comparator.comparing(Animal::weight)).get();

            if (heavierFishInSubList.weight() > maxWeight) {
                heavierFish = heavierFishInSubList;
                maxWeight = heavierFish.weight();
            }
        }
        return heavierFish;
    }

    public static Map<String, Set<ValidationError>> getAnimalsWithBadRecords(List<Animal> list) {
        Map<String, ValidationError> typeFieldErrors = AnimalValidator.validateTypeProperty(list);
        Map<String, ValidationError> sexFieldErrors = AnimalValidator.validateSexProperty(list);
        Map<String, ValidationError> numberVariablesFieldErrors = AnimalValidator.validateNumericProperties(list);

        return Stream.of(typeFieldErrors, sexFieldErrors, numberVariablesFieldErrors)
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.groupingBy(
                e -> e.getKey(),
                Collectors.mapping(e -> e.getValue(), Collectors.toSet())
            ));
    }

    public static Map<String, String> getAnimalsWithBadRecordsBetter(List<Animal> list) {
        Map<String, ValidationError> typeFieldErrors = AnimalValidator.validateTypeProperty(list);
        Map<String, ValidationError> sexFieldErrors = AnimalValidator.validateSexProperty(list);
        Map<String, ValidationError> numberVariablesFieldErrors = AnimalValidator.validateNumericProperties(list);

        return Stream.of(typeFieldErrors, sexFieldErrors, numberVariablesFieldErrors)
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.groupingBy(
                e -> e.getKey(),
                Collectors.mapping(e -> e.getValue().property(), Collectors.joining(", "))
            ));
    }

}

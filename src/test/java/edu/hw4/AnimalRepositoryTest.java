package edu.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AnimalRepositoryTest {
    private static List<Animal> basicList;

    @BeforeAll
    public static void init() {
        Animal a1 = new Animal("Gringo", Animal.Type.CAT,
            Animal.Sex.F, 11, 21, 10, false
        );
        Animal a11 = new Animal("Bilbo", Animal.Type.CAT,
            Animal.Sex.M, 14, 20, 13, true
        );

        Animal a2 = new Animal("Billy", Animal.Type.DOG,
            Animal.Sex.M, 9, 50, 14, false
        );
        Animal a22 = new Animal("Billy2", Animal.Type.DOG,
            Animal.Sex.F, 5, 41, 23, false
        );

        Animal a3 = new Animal("Rammstein", Animal.Type.FISH,
            Animal.Sex.M, 2, 11, 6, false
        );
        Animal a33 = new Animal("Fisch", Animal.Type.FISH,
            Animal.Sex.M, 3, 1, 5, false
        );

        Animal a4 = new Animal("Sinica", Animal.Type.BIRD,
            Animal.Sex.M, 2, 11, 4, false
        );
        Animal a44 = new Animal("Snigir", Animal.Type.BIRD,
            Animal.Sex.M, 3, 18, 3, false
        );

        Animal a5 = new Animal("Maguaer", Animal.Type.SPIDER,
            Animal.Sex.M, 15, 120, 1, true
        );
        Animal a55 = new Animal("Tommy", Animal.Type.SPIDER,
            Animal.Sex.M, 21, 10, 2, true
        );
        basicList = List.of(a1, a11, a2, a22, a3, a33, a4, a44, a5, a55);
    }

    @Test
    @DisplayName("sortByHeight базовый тест")
    public void sortByHeight_shouldCorrectlySort() {
        var ans = AnimalRepository.sortByHeight(basicList);

        assertThat(basicList.size() == ans.size()).isTrue();
        assertThat(ans.get(ans.size() - 1).height()).isEqualTo(120);
    }

    @Test
    @DisplayName("firstKSortedByWeight базовый тест")
    public void firstKSortedByWeight_shouldReturnKByWeight() {
        int k = 5;

        var ans = AnimalRepository.firstKSortedByWeight(basicList, k);

        assertThat(ans.size() == k).isTrue();
        assertThat(ans.get(k - 1).weight()).isEqualTo(6);
    }

    @Test
    @DisplayName("firstKSortedByWeight k больше длины списка")
    public void firstKSortedByWeight_shouldReturnAllList_whenKLargerThanListLength() {
        int k = 22;

        var ans = AnimalRepository.firstKSortedByWeight(basicList, k);
        List<Animal> expected = new ArrayList<>(basicList);
        expected.sort(Comparator.comparing(Animal::weight));
        Collections.reverse(expected);

        assertThat(expected.size() == ans.size()).isTrue();
        assertThat(ans.equals(expected)).isTrue();
    }

    @Test
    @DisplayName("firstKSortedByWeight отрицательное k")
    public void firstKSortedByWeight_shouldThrowException_whenKNegative() {
        int k = -22;

        Throwable thrown = catchThrowable(() -> {
            AnimalRepository.firstKSortedByWeight(basicList, k);
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("getTypesCount базовый тест")
    public void getTypesCount_shouldReturnMapWithTypeCount() {
        var ans = AnimalRepository.getTypesCount(basicList);

        for (Map.Entry<Animal.Type, Integer> entry : ans.entrySet()) {
            assertThat(entry.getValue() == 2);
        }
    }

    @Test
    @DisplayName("getByLongestName базовый тест")
    public void getByLongestName_shouldAnimalWithLongestName() {
        var ans = AnimalRepository.getByLongestName(basicList);

        assertThat(ans.name()).isEqualTo("Rammstein");
    }

    @Test
    @DisplayName("getByLongestName пустой список")
    public void getByLongestName_shouldReturnNull_whenListIsEmpty() {
        var ans = AnimalRepository.getByLongestName(Collections.emptyList());

        assertThat(ans == null);
    }

    @Test
    @DisplayName("getGreaterSex базовый тест")
    public void getGreaterSex_shouldReturnMale() {
        var ans = AnimalRepository.getGreaterSex(basicList);

        assertThat(ans == Animal.Sex.M);
    }

    @Test
    @DisplayName("getHeavierAnimalForEachType базовый тест")
    public void getHeavierAnimalForEachType() {
        Map<Animal.Type, Animal> expected = new HashMap<>();
        for (Animal an : basicList) {
            if (expected.containsKey(an.type())) {
                if (expected.get(an.type()).weight() < an.weight()) {
                    expected.put(an.type(), an);
                }
                continue;
            }
            expected.put(an.type(), an);
        }

        var ans = AnimalRepository.getHeavierAnimalForEachType(basicList);

        for (Map.Entry<Animal.Type, Animal> entry : ans.entrySet()) {
            assertThat(expected.containsKey(entry.getKey()));
            assertThat(expected.get(entry.getKey())).isEqualTo(entry.getValue());
        }
    }

    @Test
    @DisplayName("getKOldest базовый тест")
    public void getKOldest_shouldReturnKOldest() {
        int k = 3;

        var ans = AnimalRepository.getKOldest(basicList, k);
        List<Animal> expected = new ArrayList<>(basicList);
        expected.sort(Comparator.comparing(Animal::age));
        Collections.reverse(expected);

        assertThat(ans).isEqualTo(expected.get(k - 1));
    }

    @Test
    @DisplayName("getHeavierBelowK базовый тест")
    public void getHeavierBelowK_shouldHeavierWithHeightLessK() {
        int k = 20;

        var ans = AnimalRepository.getHeavierBelowK(basicList, k);

        assertThat(ans.get().weight()).isEqualTo(14);
    }

    @Test
    @DisplayName("countAllPaws базовый тест")
    public void countAllPaws_shouldReturnAllPaws() {
        var ans = AnimalRepository.countAllPaws(basicList);

        assertThat(ans).isEqualTo(36);
    }

    @Test
    @DisplayName("getAllWhereAgeIsNotEqualToPawsCount базовый тест")
    public void getAllWhereAgeIsNotEqualToPawsCount() {
        var ans = AnimalRepository.getAllWhereAgeIsNotEqualToPawsCount(basicList);

        assertThat(ans.size()).isEqualTo(9);
        for (Animal an : ans) {
            assertThat(an.paws()).isNotEqualTo(an.age());
        }
    }

    @Test
    @DisplayName("getAllWhichBitesAndHigherHundredCM базовый тест")
    public void getAllWhichBitesAndHigherHundredCM() {
        var ans = AnimalRepository.getAllWhichBitesAndHigherHundredCM(basicList);

        for (Animal an : ans) {
            assertThat(an.bites()).isTrue();
            assertThat(an.height() > 100).isTrue();
        }
    }

    @Test
    @DisplayName("countAnimalsWhereWeightLargerThanHeight базовый тест")
    public void countAnimalsWhereWeightLargerThanHeight() {
        int animalsCount = 0;
        for (Animal an : basicList) {
            if (an.weight() > an.height()) {
                animalsCount++;
            }
        }

        var ans = AnimalRepository.countAnimalsWhereWeightLargerThanHeight(basicList);

        assertThat(ans).isEqualTo(animalsCount);
    }

    @Test
    @DisplayName("getAllWhereNameHasMoreThanTwoWords базовый тест")
    public void getAllWhereNameHasMoreThanTwoWords() {
        List<Animal> expected = new ArrayList<>();
        for (Animal an : basicList) {
            if (an.name().split(" ").length > 2) {
                expected.add(an);
            }
        }

        var ans = AnimalRepository.getAllWhereNameHasMoreThanTwoWords(basicList);

        assertThat(ans.containsAll(expected)).isTrue();
    }

    @Test
    @DisplayName("containsDogHeightLargerThanK базовый тест")
    public void containsDogHeightLargerThanK() {
        int k = 10;
        boolean expected = false;
        for (Animal an : basicList) {
            if (an.type().equals(Animal.Type.DOG) && an.height() > 10) {
                expected = true;
                break;
            }
        }

        var ans = AnimalRepository.containsDogHeightLargerThanK(basicList, k);

        assertThat(expected == ans);
    }

    @Test
    @DisplayName("countWeightSumByAge базовый тест")
    public void countWeightSumByAge() {
        int k = 3;
        int l = 6;
        int sum = 0;
        for (Animal an : basicList) {
            if (an.age() >= k && an.age() <= l) {
                sum += an.weight();
            }
        }

        var ans = AnimalRepository.countWeightSumByAge(basicList, k, l);

        assertThat(ans).isEqualTo(sum);
    }

    @Test
    @DisplayName("sortByTypeThenSexThenName базовый тест")
    public void sortByTypeThenSexThenName() {
        var expected = new ArrayList<>(basicList);
        expected.sort(Comparator.comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name));

        var ans = AnimalRepository.sortByTypeThenSexThenName(basicList);

        assertThat(ans.containsAll(expected));
    }

    @Test
    @DisplayName("isSpiderBitesOftenThanDog базовый тест")
    public void isSpiderBitesOftenThanDog() {
        int dogBitesCount = 0;
        int spiderBitesCount = 0;
        for (Animal an : basicList) {
            if (an.type().equals(Animal.Type.DOG) && an.bites()) {
                dogBitesCount++;
            } else if (an.type().equals(Animal.Type.SPIDER) && an.bites()) {
                spiderBitesCount++;
            }
        }

        var ans = AnimalRepository.isSpiderBitesOftenThanDog(basicList);

        assertThat(ans).isEqualTo(spiderBitesCount > dogBitesCount);
    }

    @Test
    @DisplayName("getHeavierFish базовый тест")
    public void getHeavierFish() {
        Animal heavierFish = null;
        int max = 0;
        for (Animal an : basicList) {
            if (an.type().equals(Animal.Type.FISH)) {
                if (an.weight() > max) {
                    heavierFish = an;
                    max = an.weight();
                }
            }
        }

        var ans = AnimalRepository.getHeavierFish(basicList);

        assertThat(ans).isEqualTo(heavierFish);
    }

    @Test
    @DisplayName("getAnimalsWithBadRecords базовый тест")
    public void getAnimalsWithBadRecords() {
        List<Animal> animalsWithErrorsInField = new ArrayList<>();
        for (Animal an : basicList) {
            if (an.type() == null || an.sex() == null
                || an.height() < 0 || an.weight() < 0 || an.age() < 0) {
                animalsWithErrorsInField.add(an);
            }
        }

        var ans = AnimalRepository.getAnimalsWithBadRecords(basicList);

        assertThat(ans.size() == animalsWithErrorsInField.size()).isTrue();
        for (Animal an : animalsWithErrorsInField) {
            assertThat(ans.containsKey(an.name()));
        }
    }

}

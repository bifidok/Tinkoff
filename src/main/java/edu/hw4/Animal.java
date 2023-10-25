package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    private static final int DOG_AND_CAT_PAWS_COUNT = 4;
    private static final int BIRD_PAWS_COUNT = 2;
    private static final int FISH_PAWS_COUNT = 0;
    private static final int SPIDER_PAWS_COUNT = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> DOG_AND_CAT_PAWS_COUNT;
            case BIRD -> BIRD_PAWS_COUNT;
            case FISH -> FISH_PAWS_COUNT;
            case SPIDER -> SPIDER_PAWS_COUNT;
        };
    }
}

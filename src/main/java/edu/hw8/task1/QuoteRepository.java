package edu.hw8.task1;

import java.util.List;

public class QuoteRepository implements Repository {
    private final static String DEFAULT_QUOTE = "Сам";
    private final static List<String> QUOTES = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    );

    @Override
    public String findByKeyWord(String keyWord) {
        if (keyWord == null) {
            return null;
        }
        for (String quote : QUOTES) {
            if (quote.contains(keyWord)) {
                return quote;
            }
        }
        return DEFAULT_QUOTE;
    }
}

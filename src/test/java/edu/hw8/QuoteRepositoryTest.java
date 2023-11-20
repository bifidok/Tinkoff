package edu.hw8;

import edu.hw8.task1.QuoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuoteRepositoryTest {
    @Test
    @DisplayName("Поиск по ключевому слову")
    public void findByKeyWord(){
        String keyWord = "оскробления";
        QuoteRepository quoteRepository = new QuoteRepository();

        var quote = quoteRepository.findByKeyWord(keyWord);

        assertThat(quote).isNotNull();
    }

    @Test
    @DisplayName("Ключевое слово null")
    public void findByKeyWord_shouldReturnNull(){
        QuoteRepository quoteRepository = new QuoteRepository();

        var quote = quoteRepository.findByKeyWord(null);

        assertThat(quote).isNull();
    }
}

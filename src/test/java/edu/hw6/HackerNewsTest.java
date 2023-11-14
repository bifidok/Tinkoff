package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HackerNewsTest {
    @Test
    @DisplayName("Массив историй")
    public void hackerNewsTopStories() {
        var topStories = HackerNews.hackerNewsTopStories();

        assertThat(topStories).isNotEmpty();
    }

    @Test
    @DisplayName("Название истории")
    public void news_shouldCorrectlyParseName() {
        var topStories = HackerNews.hackerNewsTopStories();
        var name = HackerNews.news(topStories[1]);

        assertThat(name).isNotNull();
    }

    @Test
    @DisplayName("Несущиствующий id")
    public void news_shouldReturnEmptyString() {
        var name = HackerNews.news(Integer.MIN_VALUE);

        assertThat(name).isEqualTo("");
    }
}

package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConsoleHangmanTest {
    @Test
    @DisplayName("Некорректная длина загаданного слова")
    void run_shouldStopTheGame() {
        ConsoleHangman consoleHangman = new ConsoleHangman(6);
        consoleHangman.run();
    }
}

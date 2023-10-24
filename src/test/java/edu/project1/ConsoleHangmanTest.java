package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsoleHangmanTest {
    private GameSession session = mock(GameSession.class);
    private InputOutputSystem inputOutputSystem = mock(InputOutputSystem.class);

    @Test
    @DisplayName("Некорректная длина слова")
    void run_shouldTurnOff_whenWordHasInvalidLength() {
        ConsoleHangman game = new ConsoleHangman(inputOutputSystem, session, 5);
        SessionState message = new SessionState("", 5, 5, "");

        when(session.startNewSession()).thenReturn(message);
        try {
            game.run();
        } catch (Exception exp) {
            assertThat(exp).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word has incorrect length");
        }

        verify(session, never()).checkGameState();
    }

    @Test
    @DisplayName("Начальное количество попыток ноль")
    void run_shouldTurnOff_whenNoAttempts() {
        ConsoleHangman game = new ConsoleHangman(inputOutputSystem, session, 0);
        SessionState message = new SessionState("", 0, 0, "dasfads");

        when(session.startNewSession()).thenReturn(message);
        when(session.checkGameState()).thenReturn(GameState.STOP);
        game.run();

        verify(inputOutputSystem, never()).input();
        verify(inputOutputSystem, never()).printGameState(null);
        verify(inputOutputSystem, never()).printGuessResult(null);
    }

    @Test
    @DisplayName("Опечатка на вводе")
    void run_shouldSaveAttempts_whenInvalidInput() {
        ConsoleHangman game = new ConsoleHangman(inputOutputSystem, session, 2);
        SessionState message = new SessionState("", 2, 2, "dasfads");

        when(session.startNewSession()).thenReturn(message);
        when(session.checkGameState()).thenReturn(GameState.RUN);
        when(inputOutputSystem.input()).thenReturn(" ", "1", "")
            .thenThrow(new RuntimeException("End of cycle"));
        try {
            game.run();
        } catch (Exception exp) {
            assertThat(exp).isInstanceOf(RuntimeException.class);
        }
        verify(inputOutputSystem, never()).printGuessResult(message);
        assertThat(message.curAttempts()).isEqualTo(message.maxAttempts());
    }
}

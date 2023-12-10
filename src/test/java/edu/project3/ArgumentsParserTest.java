package edu.project3;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class ArgumentsParserTest {
    @Test
    @DisplayName("Все аргументы")
    public void parse_shouldParseAllArgs() {
        LocalDate from = LocalDate.of(2015, 10, 20);
        LocalDate to = LocalDate.of(2015, 10, 21);
        String[] args = new String[] {
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            from.getYear() + "-" + from.getMonthValue() + "-" + from.getDayOfMonth(),
            to.getYear() + "-" + to.getMonthValue() + "-" + to.getDayOfMonth(),
            "adoc"
        };

        ArgumentsContainer container = ArgumentsParser.parse(args);

        assertThat(container.from()).isEqualTo(from);
        assertThat(container.to()).isEqualTo(to);
        assertThat(container.file()).isNotNull();
        assertThat(container.outputFormat()).isEqualTo(OutputFormat.ADOC);
    }

    @Test
    @DisplayName("Только файл")
    public void parse_shouldParseArg_whenOneArgument() {
        String[] args = new String[] {
            "C:\\Users\\striz\\IdeaProjects\\project-template\\nginx_logs.txt",
        };

        ArgumentsContainer container = ArgumentsParser.parse(args);

        assertThat(container.from()).isNull();
        assertThat(container.to()).isNull();
        assertThat(container.file()).isNotNull();
        assertThat(container.outputFormat()).isNull();
    }

    @Test
    @DisplayName("Файл и from")
    public void parse_shouldParseArgs_whenTwoArguments() {
        LocalDate from = LocalDate.of(2015, 10, 20);
        String[] args = new String[] {
            "C:\\Users\\striz\\IdeaProjects\\project-template\\nginx_logs.txt",
            from.getYear() + "-" + from.getMonthValue() + "-" + from.getDayOfMonth(),
        };

        ArgumentsContainer container = ArgumentsParser.parse(args);

        assertThat(container.from()).isEqualTo(from);
        assertThat(container.to()).isNull();
        assertThat(container.file()).isNotNull();
        assertThat(container.outputFormat()).isNull();
    }

    @Test
    @DisplayName("Нет аргументов")
    public void parse_shouldThrowException_whenNoArguments() {
        String[] args = new String[] {
        };

        Throwable thrown = catchThrowable(() -> {
            ArgumentsParser.parse(args);
        });
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Not enough arguments");
    }
}

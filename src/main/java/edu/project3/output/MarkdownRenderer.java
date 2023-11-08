package edu.project3.output;

import edu.project3.ArgumentsContainer;
import edu.project3.Statistic;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Map;

@SuppressWarnings("MultipleStringLiterals")
public class MarkdownRenderer implements StatisticRenderer {
    private final static String MARKDDOWN_FILE_PATH = "C:\\Users\\striz\\IdeaProjects\\project-template"
        + "\\src\\main\\java\\edu\\project3\\output\\statistic.md";
    private final Statistic statistic;
    private final ArgumentsContainer container;

    public MarkdownRenderer(Statistic statistic, ArgumentsContainer container) {
        this.statistic = statistic;
        this.container = container;
    }

    @Override
    public String render() {
        File file = new File(MARKDDOWN_FILE_PATH);
        StringBuilder builder = new StringBuilder();
        builder.append(generateGeneralTable())
            .append(generateResourceTable())
            .append(generateResponseCodeTable())
            .append(generateUseragentTable())
            .append(generateDateTable());
        try {
            Files.writeString(file.toPath(), builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getPath();
    }

    private String generateGeneralTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#### Общая информация\n\n")
            .append("|        Метрика        |     Значение |\n")
            .append("|:---------------------:|-------------:|\n")
            .append("|       Файл(-ы)        | ").append(statistic.fileName()).append(" |\n")
            .append("|    Начальная дата     |  ").append(container.from()).append(" |\n")
            .append("|     Конечная дата     |  ").append(container.to()).append(" |\n")
            .append("|  Количество запросов  |  ").append(statistic.logsCount()).append(" |\n")
            .append("| Средний размер ответа |  ").append(statistic.averageServerResponseSize()).append("b |\n");
        return builder.toString();
    }

    private String generateResourceTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#### Запрашиваемые ресурсы\n\n")
            .append("|     Ресурс      | Количество |\n")
            .append("|:---------------:|-----------:|\n");
        for (Map.Entry<String, Integer> entry : statistic.resourceToRequestCount()) {
            builder
                .append("|  `")
                .append(entry.getKey())
                .append("`  |   ")
                .append(entry.getValue())
                .append("  |\n");
        }
        return builder.toString();
    }

    private String generateUseragentTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#### Пользовательский агент\n\n")
            .append("|     Агент      | Количество |\n")
            .append("|:---------------:|-----------:|\n");
        for (Map.Entry<String, Integer> entry : statistic.useragentToCount()) {
            builder
                .append("|  ")
                .append(entry.getKey())
                .append("  |   ")
                .append(entry.getValue())
                .append("  |\n");
        }
        return builder.toString();
    }

    private String generateDateTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#### Даты активности\n\n")
            .append("|     Дата      | Количество |\n")
            .append("|:---------------:|-----------:|\n");
        for (Map.Entry<LocalDate, Integer> entry : statistic.dateToCount()) {
            builder
                .append("|  ")
                .append(entry.getKey())
                .append("  |   ")
                .append(entry.getValue())
                .append("  |\n");
        }
        return builder.toString();
    }

    private String generateResponseCodeTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#### Коды ответа\n\n")
            .append("| Код |          Имя          | Количество |\n")
            .append("|:---:|:---------------------:|-----------:|\n");
        for (Map.Entry<String, Integer> entry : statistic.responseCodeToCount()) {
            builder.append("| ")
                .append(entry.getKey())
                .append(" |  ")
                .append(REASONS.get(entry.getKey()))
                .append(" |  ")
                .append(entry.getValue())
                .append("  |\n");
        }
        return builder.toString();
    }
}

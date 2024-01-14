package edu.project3.output;

import edu.project3.ArgumentsContainer;
import edu.project3.Statistic;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Map;

@SuppressWarnings("MultipleStringLiterals")
public class ADocRenderer implements StatisticRenderer {

    private final static String ADOC_FILE_PATH = "C:\\Users\\striz\\IdeaProjects\\project-template"
        + "\\src\\main\\java\\edu\\project3\\output\\statistic.adoc";
    private final Statistic statistic;
    private final ArgumentsContainer container;

    public ADocRenderer(Statistic statistic, ArgumentsContainer container) {
        this.statistic = statistic;
        this.container = container;
    }

    @Override
    public String render() {
        File file = new File(ADOC_FILE_PATH);
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
        builder.append("\nОбщая информация\n")
            .append("|===\n")
            .append("|Метрика|Значение\n")
            .append("|Файл(-ы)|").append(statistic.fileName()).append("\n")
            .append("|Начальная дата|").append(container.from()).append("\n")
            .append("|Конечная дата|").append(container.to()).append("\n")
            .append("|Количество запросов|").append(statistic.logsCount()).append("\n")
            .append("|Средний размер ответа|").append(statistic.averageServerResponseSize()).append("b\n")
            .append("|===\n");
        return builder.toString();
    }

    private String generateResourceTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nЗапрашиваемые ресурсы\n")
            .append("|===\n")
            .append("|Ресурс|Количество\n");
        for (Map.Entry<String, Integer> entry : statistic.resourceToRequestCount()) {
            builder
                .append("|")
                .append(entry.getKey())
                .append("|")
                .append(entry.getValue())
                .append("\n");
        }
        builder.append("|===\n");
        return builder.toString();
    }

    private String generateUseragentTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nПользовательский агент\n")
            .append("|===\n")
            .append("|Агент|Количество \n");
        for (Map.Entry<String, Integer> entry : statistic.useragentToCount()) {
            builder
                .append("|")
                .append(entry.getKey())
                .append("|")
                .append(entry.getValue())
                .append("\n");
        }
        builder.append("|===\n");
        return builder.toString();
    }

    private String generateDateTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nДаты активности\n")
            .append("|===\n")
            .append("|Дата|Количество\n");
        for (Map.Entry<LocalDate, Integer> entry : statistic.dateToCount()) {
            builder
                .append("|")
                .append(entry.getKey())
                .append("|")
                .append(entry.getValue())
                .append("\n");
        }
        builder.append("|===\n");
        return builder.toString();
    }

    private String generateResponseCodeTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nКоды ответа\n")
            .append("|===\n")
            .append("|Код |Имя|Количество\n");
        for (Map.Entry<String, Integer> entry : statistic.responseCodeToCount()) {
            builder.append("|")
                .append(entry.getKey())
                .append("|")
                .append(REASONS.get(entry.getKey()))
                .append("|")
                .append(entry.getValue())
                .append("\n");
        }
        builder.append("|===\n");
        return builder.toString();
    }
}

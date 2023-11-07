package edu.project3.output;

import edu.project3.ArgumentsContainer;
import edu.project3.Statistic;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Map;
import static java.util.Map.entry;

public class MarkdownRenderer implements StatisticRenderer {
    private final String MARKDDOWN_FILE_PATH = "C:\\Users\\striz\\IdeaProjects\\project-template" +
        "\\src\\main\\java\\edu\\project3\\output\\statistic.md";
    private final Statistic statistic;
    private final ArgumentsContainer container;
    private static final Map<String, String> REASONS = Map.ofEntries(
        // informational
        entry("100", "Continue"),
        entry("101", "Switching Protocol"),
        entry("102", "Processing"),
        entry("103", "Early Hints"),

        // successful
        entry("200", "OK"),
        entry("201", "Created"),
        entry("202", "Accepted"),
        entry("203", "Non-Authoritative Information"),
        entry("204", "No Content"),
        entry("205", "Reset Content"),
        entry("206", "Partial Content"),
        entry("207", "Multi Status"),
        entry("208", "Already Reported"),
        entry("226", "IM Used"),

        // redirection
        entry("300", "Multiple Choice"),
        entry("301", "Moved Permanently"),
        entry("302", "Found"),
        entry("303", "See Other"),
        entry("304", "Not Modified"),
        entry("305", "Use Proxy"), // deprecated
        entry("307", "Temporary Redirect"),
        entry("308", "Permanent Redirect"),

        // client error
        entry("400", "Bad Request"),
        entry("401", "Unauthorized"),
        entry("402", "Payment Required"),
        entry("403", "Forbidden"),
        entry("404", "Not Found"),
        entry("405", "Method Not Allowed"),
        entry("406", "Not Acceptable"),
        entry("407", "Proxy Authentication Required"),
        entry("408", "Request Timeout"),
        entry("409", "Conflict"),
        entry("410", "Gone"),
        entry("411", "Length Required"),
        entry("412", "Precondition Failed"),
        entry("413", "Payload Too Long"),
        entry("414", "URI Too Long"),
        entry("415", "Unsupported Media Type"),
        entry("416", "Range Not Satisfiable"),
        entry("417", "Expectation Failed"),
        entry("418", "I'm a Teapot"),
        entry("421", "Misdirected Request"),
        entry("422", "Unprocessable Entity"),
        entry("423", "Locked"),
        entry("424", "Failed Dependency"),
        entry("425", "Too Early"),
        entry("426", "Upgrade Required"),
        entry("428", "Precondition Required"),
        entry("429", "Too Many Requests"),
        entry("431", "Request Header Fields Too Large"),
        entry("451", "Unavailable for Legal Reasons"),

        // server error
        entry("500", "Internal Server Error"),
        entry("501", "Not Implemented"),
        entry("502", "Bad Gateway"),
        entry("503", "Service Unavailable"),
        entry("504", "Gateway Timeout"),
        entry("505", "HTTP Version Not Supported"),
        entry("506", "Variant Also Negotiates"),
        entry("507", "Insufficient Storage"),
        entry("508", "Loop Detected"),
        entry("510", "Not Extended"),
        entry("511", "Network Authentication Required")
    );
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
    private String generateResponseCodeTable(){
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

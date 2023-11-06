package edu.project3;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ArgumentsParser {
    private final static Pattern URL_PATTERN = Pattern.compile("^(http)?.*\\.com");
    private final static Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String MARKDOWN = "markdown";
    private final static String ADOC = "adoc";

    private ArgumentsParser() {
    }

    public static ArgumentsContainer parse(String[] args) {
        int argumentsCount = args.length;
        if (argumentsCount < 1) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        String urlString = null;
        String filePath = null;
        LocalDate from = null;
        LocalDate to = null;
        OutputFormat format = null;
        for (int i = 0; i < argumentsCount; i++) {
            if (urlString == null && filePath == null) {
                if (isURL(args[i])) {
                    urlString = args[i];
                } else {
                    filePath = args[i];
                }
                continue;
            }
            if (isDate(args[i])) {
                if (from == null) {
                    from = parseDate(args[i]);
                    continue;
                } else if (to == null) {
                    to = parseDate(args[i]);
                    continue;
                }
            }
            if (args[i].equals(MARKDOWN)) {
                format = OutputFormat.MARKDOWN;
            } else if (args[i].equals(ADOC)) {
                format = OutputFormat.ADOC;
            }
        }
        return new ArgumentsContainer(
            urlString == null ? new File(filePath) : URLToFileConverter.convert(urlString),
            from,
            to,
            format
        );
    }

    private static boolean isURL(String path) {
        var matcher = URL_PATTERN.matcher(path);
        return matcher.find();
    }

    private static boolean isDate(String date) {
        var matcher = DATE_PATTERN.matcher(date);
        return matcher.matches();
    }

    private static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return parsedDate;
    }
}

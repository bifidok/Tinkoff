package edu.project3;

import edu.project3.output.ADocRenderer;
import edu.project3.output.MarkdownRenderer;
import edu.project3.output.StatisticPrinter;
import edu.project3.output.StatisticRenderer;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        String [] testArgs = new String [] {
            "C:\\Users\\striz\\IdeaProjects\\project-template\\nginxLogs.txt",
            "2015-05-20",
            "2015-05-21",
            "adoc"
        };
        ArgumentsContainer argumentsContainer = ArgumentsParser.parse(testArgs);
        LogAnalyzer parser = new LogAnalyzer();
        Statistic statistic = parser.analyze(argumentsContainer.file(),argumentsContainer.from(),argumentsContainer.to());
        StatisticRenderer renderer;
        switch (argumentsContainer.outputFormat()){
            case ADOC -> renderer = new ADocRenderer(statistic,argumentsContainer);
            default -> renderer = new MarkdownRenderer(statistic,argumentsContainer);
        }
        String path = renderer.render();
        StatisticPrinter.print(path);
    }
}

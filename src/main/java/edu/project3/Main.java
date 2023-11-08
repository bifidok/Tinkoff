package edu.project3;

import edu.project3.output.ADocRenderer;
import edu.project3.output.MarkdownRenderer;
import edu.project3.output.StatisticPrinter;
import edu.project3.output.StatisticRenderer;

public final class Main {

    private Main() {
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        ArgumentsContainer argumentsContainer = ArgumentsParser.parse(args);
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        Statistic statistic =
            logAnalyzer.analyze(argumentsContainer.file(), argumentsContainer.from(), argumentsContainer.to());
        if (statistic == null) {
            System.out.println("Logs not found");
            return;
        }
        StatisticRenderer renderer;
        if (argumentsContainer.outputFormat().equals(OutputFormat.MARKDOWN)) {
            renderer = new MarkdownRenderer(statistic, argumentsContainer);
        } else {
            renderer = new ADocRenderer(statistic, argumentsContainer);
        }
        String path = renderer.render();
        StatisticPrinter.print(path);
    }
}

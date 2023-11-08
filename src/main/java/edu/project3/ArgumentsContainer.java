package edu.project3;

import java.io.File;
import java.time.LocalDate;

public record ArgumentsContainer(File file, LocalDate from, LocalDate to, OutputFormat outputFormat) {
}

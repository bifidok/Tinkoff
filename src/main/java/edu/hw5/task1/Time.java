package edu.hw5.task1;

public record Time(int hours, int minutes, int seconds) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (hours > 0) {
            builder.append(hours).append("ч ");
        }
        if (minutes > 0) {
            builder.append(minutes).append("м ");
        }
        if (seconds > 0 || builder.length() == 0) {
            builder.append(seconds).append("с ");
        }
        return builder.toString().stripTrailing();
    }
}

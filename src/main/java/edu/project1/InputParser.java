package edu.project1;

public class InputParser {
    private final static String RESTART = "rr";
    private final static String END = "end";

    public InputState parse(String input) {
        switch (input) {
            case RESTART -> {
                return InputState.RESTART;
            }
            case END -> {
                return InputState.END;
            }
            default -> {
                if (input.length() == 1 && input.matches("[a-z]")) {
                    return InputState.CHARACTER;
                }
                return InputState.INVALID;
            }
        }
    }
}


package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BracketClusterizer {
    private BracketClusterizer() {

    }

    public static List<String> clusterize(String brackets) {
        Stack<Character> bracketStack = new Stack<>();
        List<String> answer = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);
            if (bracket == '(') {
                bracketStack.push(bracket);
            } else if (bracket == ')') {
                if (bracketStack.empty()) {
                    throw new IllegalArgumentException("Unbalanced brackets");
                }
                bracketStack.pop();
            }
            if (bracketStack.empty()) {
                answer.add(brackets.substring(start, i + 1));
                start = i + 1;
            }
        }
        return answer;
    }
}

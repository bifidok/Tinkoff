package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BracketClusterizer {
    private BracketClusterizer() {

    }

    public static List<String> clusterize(String brackets) {
        Stack<Character> bracketContainer = new Stack<>();
        List<String> ans = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);
            if (bracket == '(') {
                bracketContainer.push(bracket);
            } else if (bracket == ')') {
                if (bracketContainer.empty()) {
                    throw new IllegalArgumentException("Unbalanced brackets");
                }
                bracketContainer.pop();
            }
            if (bracketContainer.empty()) {
                ans.add(brackets.substring(start, i + 1));
                start = i + 1;
            }
        }
        return ans;
    }
}

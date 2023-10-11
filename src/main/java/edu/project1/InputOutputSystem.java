package edu.project1;

import java.util.Scanner;

public class InputOutputSystem {
    Scanner scanner;

    public InputOutputSystem() {
        scanner = new Scanner(System.in);
    }

    public String input(){
        String input = scanner.nextLine();
        return input;
    }
    public void output(String message){
        System.out.println(message);
    }
}

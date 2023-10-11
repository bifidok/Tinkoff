package edu.project1;

public class Message {
    private String guessResult;
    private int curAttempts;
    private int maxAttempts;
    private String maskedWord;

    public Message(String guessResult, String maskedWord, int curAttempts, int maxAttempts) {
        this.maskedWord = maskedWord;
        this.guessResult = guessResult;
        this.curAttempts = curAttempts;
        this.maxAttempts = maxAttempts;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public int getCurAttempts() {
        return curAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public void setCurAttempts(int curAttempts) {
        this.curAttempts = curAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void setMaskedWord(String maskedWord) {
        this.maskedWord = maskedWord;
    }
}

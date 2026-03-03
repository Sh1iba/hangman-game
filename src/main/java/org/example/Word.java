package main.java.org.example;

public class Word {
    private final String word;
    private String maskedWord;

    Word(String word) {
        this.word = word;
        this.maskedWord = "_".repeat(word.length());
    }

    public void openLetter(char letter) {
        StringBuilder stringBuilder = new StringBuilder(maskedWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                stringBuilder.setCharAt(i, letter);
            }
        }
        maskedWord = stringBuilder.toString();
    }

    public boolean containsLetter(char letter) {
        return word.indexOf(letter) >= 0;
    }

    public boolean isWordGuessed() {
        return maskedWord.equals(word);
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public String getWord() {
        return word;
    }

}

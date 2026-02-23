package main.java.org.example;

public class WordHandler {
    private final String word;
    private String maskedWord;

    WordHandler(String word){
        this.word = word;
        this.maskedWord = "_".repeat(word.length());
    }

    public boolean guessLetter(char letter) {
        StringBuilder stringBuilder = new StringBuilder(maskedWord);
        boolean flag = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                stringBuilder.setCharAt(i, letter);
                flag = true;
            }
        }
        maskedWord = stringBuilder.toString();
        return flag;
    }

    public boolean isWordGuessed(){
        return maskedWord.equals(word);
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public String getWord() {
        return word;
    }

}

package main.java.org.example;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsedLetters {

    private final Collator collator = Collator.getInstance(new Locale("ru"));
    private final List<String> letters = new ArrayList<>();

    public void add(String letter) {
        letters.add(letter);
    }

    public boolean contains(String letter) {
        return letters.contains(letter);
    }

    public List<String> getSorted() {
        List<String> sorted = new ArrayList<>(letters);
        sorted.sort(collator);
        return sorted;
    }

    public void clear() {
        letters.clear();
    }

}

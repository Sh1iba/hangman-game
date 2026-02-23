package main.java.org.example;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {

    private static final String NEW_GAME = "1";
    private static final String EXIT = "2";

    private final Scanner scanner = new Scanner(System.in);
    private final Pattern CYRILLIC = Pattern.compile("[а-яё]");
    private final Collator collator = Collator.getInstance(new Locale("ru"));
    private final List<String> usedLetters = new ArrayList<>();

    public boolean readMenuChoice() {
        while (true) {
            System.out.println();
            System.out.printf("%s. Новая игра  %n", NEW_GAME);
            System.out.printf("%s. Выйти из игры  %n", EXIT);

            String input = scanner.nextLine();

            switch (input){
                case NEW_GAME: return true;
                case EXIT: return false;
                default:
                    System.out.printf("Введите %s или %s %n", NEW_GAME, EXIT);
            }
        }
    }

    public char inputRussianLetter() {
        while (true) {
            System.out.println("Введите букву: ");
            String letter = scanner.nextLine().toLowerCase();

            if (!isValidRussianLetter(letter)) {
                System.out.println("Введите корректное значение: (одну букву на русском)");
                continue;
            }
            if (usedLetters.contains(letter)) {
                System.out.println("Вы уже использовали эту букву");
                continue;
            }
            usedLetters.add(letter);
            return letter.charAt(0);
        }
    }

    public List<String> getSortedUsedLetters() {
        List<String> sorted = new ArrayList<>(usedLetters);
        sorted.sort(collator);
        return sorted;
    }

    public void clearUsedLetters(){
        usedLetters.clear();
    }

    private boolean isValidRussianLetter(String letter){
        return letter.length() == 1 && CYRILLIC.matcher(letter).matches();
    }
}

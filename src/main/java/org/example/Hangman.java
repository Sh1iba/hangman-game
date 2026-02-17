package main.java.org.example;

import java.text.Collator;
import java.util.*;
import java.util.regex.Pattern;

public class Hangman {
    private static final String NEW_GAME = "1";
    private static final String EXIT = "2";

    private final Scanner scanner = new Scanner(System.in);
    private final Pattern CYRILLIC = Pattern.compile("[а-яё]+");
    Collator collator = Collator.getInstance(new Locale("ru"));
    
    private String word;
    private String hideWord;
    private final List<String> usedLetters = new ArrayList<>();

    public void startGame(){
        while (true) {
            if(startGameMenu()){
                startGameLoop();
            }
            else return;

        }
    }

    private void startGameLoop(){
         byte mistakeCount = 0;
        try {
            word = Dictionary.getRandomWord();
        } catch (IllegalStateException e) {
            System.err.println("\n❌ Не удалось начать игру: " + e.getMessage());
            System.err.println("Возврат в главное меню.");
            return; // возвращаемся в меню
        }
         hideWord = hideWord(word);
         Gallow gallow = Gallow.START_STATE;
         printGameState(gallow,mistakeCount);
         String guessedWord;
         while (true){

             guessedWord = guessLetter();

             if(guessedWord.equals(hideWord)){
                 mistakeCount++;
                 gallow = gallow.nextState();
                 printGameState(gallow,mistakeCount);
                 if(gallow.getState().equals(Gallow.FINAL_STATE.getState())){
                     System.out.println("ВЫ ПРОИГРАЛИ \uD83D\uDEA9");
                     System.out.println("Загаданное слово: " + word);
                     usedLetters.clear();
                     break;
                 }
             }else {
                 hideWord = guessedWord;
                 printGameState(gallow,mistakeCount);
                 if (guessedWord.equals(word)){
                     System.out.println("ВЫ ВЫИГРАЛИ \uD83C\uDFC6");
                     usedLetters.clear();
                     break;
                 }
             }

         }
    }

    private void printGameState(Gallow gallow,byte mistakeCount){
        System.out.println(gallow.getState());
        System.out.println("Слово: " + hideWord);
        System.out.println("Счетчик ошибок: " + mistakeCount);
        System.out.println("Использованные буквы: " + usedLetters);
    }

    private String guessLetter(){
        char letter  = letterValidation().charAt(0);
        StringBuilder stringBuilder = new StringBuilder(hideWord);
        for(int i = 0; i<word.length(); i++){
            if(word.charAt(i) == letter){
                stringBuilder.setCharAt(i,letter);
            }
        }
        return stringBuilder.toString();
    }

    private String letterValidation(){
        System.out.println("Введите букву: ");
        while (true){
            String letter = scanner.nextLine().toLowerCase();
            if(letter.length() != 1 || !CYRILLIC.matcher(letter).matches()){
                System.out.println("Введите корректное значение: (одну букву на русском)");
            }
            else{
                if(usedLetters.contains(letter)){
                    System.out.println("Вы уже использовали эту букву");
                }else {
                    usedLetters.add(letter);
                    usedLetters.sort(collator);
                    return letter;
                }
            }
        }
    }

    private String hideWord(String word) {
        return "_".repeat(word.length());
    }

    private boolean startGameMenu(){
        System.out.println();
        System.out.printf("Введите %s: Новая игра  %n",  NEW_GAME);
        System.out.printf("Введите %s: Выйти из игры  %n", EXIT);
        while (true){
            String choice = scanner.nextLine();
            if(choice.equals(NEW_GAME)){
                return true;
            }
            if (choice.equals(EXIT)) {
                return false;
            }
            System.out.printf("Введите корректное значение: %s или %s  %n", NEW_GAME, EXIT);

        }
    }
}

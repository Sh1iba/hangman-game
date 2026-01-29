package main.java.org.example;

import java.util.*;
import java.util.regex.Pattern;

public class Hangman {
    private static final String NEW_GAME = "1";
    private static final String EXIT = "2";
    private final Scanner scanner = new Scanner(System.in);
    private final Pattern CYRILLIC = Pattern.compile("[а-я]+");
    String word;
    String hideWord;
    Set<String> usedWord = new HashSet<>();

    //метод управления логикой игры
    public void startGame(){
        while (true) {
            if(startGameMenu()){
                startGameLoop();
            }else {
                return;
            }
        }
    }

    //запускает игровой цикл
    private void startGameLoop(){
         byte mistakeCount = 0;
         word = Dictionary.getRandomWord();
         hideWord = Dictionary.hideWord(word);
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
                     System.out.println("ВЫ ПРОИГРАЛИ (ᴗ_ ᴗ。)");
                     System.out.println("Загаданное слово: " + word);
                     usedWord.clear();
                     break;
                 }
             }else {
                 hideWord = guessedWord;
                 printGameState(gallow,mistakeCount);
                 if (guessedWord.equals(word)){
                     System.out.println("ВЫ ВЫИГРАЛИ \uD83C\uDFC6");
                     usedWord.clear();
                     break;
                 }
             }

         }
    }

    //выводит в консоль текущее состояние игры
    private void printGameState(Gallow gallow,byte mistakeCount){
        System.out.println(gallow.getState());
        System.out.println("Слово: " + hideWord);
        System.out.println("Счетчик ошибок: " + mistakeCount);
        System.out.println("Использованные буквы: " + usedWord);
    }

    //возвращает строку с отгаданными или нет буквами
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

    //возвращает введенную пользователем букву
    private String letterValidation(){
        String letter = "";
        System.out.println("Введите букву: ");
        while (true){
            letter = scanner.nextLine().toLowerCase();
            if(letter.length() != 1 || !CYRILLIC.matcher(letter).matches()){
                System.out.println("Введите корректное значение: (одну букву на русском)");
            }
            else{
                if(usedWord.contains(letter)){
                    System.out.println("Вы уже использовали эту букву");
                }else {
                    usedWord.add(letter);
                    return letter;
                }
            }
        }
    }


    //метод показа приветственного меню для начала игры или ее завершения
    private boolean startGameMenu(){
        System.out.println("\nВведите " + NEW_GAME + ": Новая игра\n" + "Введите " + EXIT + ": Выйти из игры");
        String choice;
        while (true){
            choice = scanner.nextLine();
            if(choice.equals(NEW_GAME)){
                return true;
            } else if (choice.equals(EXIT)) {
                return false;
            }else{
                System.out.println("Введите корректное значение: " + NEW_GAME + " или " + EXIT);
            }
        }
    }
}

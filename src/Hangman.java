import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    private static final String NEW_GAME = "1";
    private static final String EXIT = "2";

    //метод управления логикой игры
    public void startGame(){
        startGameMenu();
        startGameLoop();
    }

    public void startGameLoop(){
         String word = DictionaryReader.readWordFromFile();
         String hideWord = DictionaryReader.hideWord(word);
         System.out.println("Слово: " + hideWord);
         int mistakeCount = 0;
         System.out.println("Счетчик ошибок: " + mistakeCount);
         guessLetter(word,hideWord);
    }

    public String guessLetter(String word, String hideWord){
        Scanner scanner = new Scanner(System.in);
        String letter = "";
        System.out.println("Введите букву: ");
        while (true){
            letter = scanner.nextLine().toLowerCase();
            if(letter.length() != 1 || !letter.matches("[а-я]+")){
                System.out.println("Введите корректное значение (одну букву): ");
            }
            else{
                break;
            }
        }
        char temp  = letter.charAt(0);
        StringBuilder stringBuilder = new StringBuilder(hideWord);
        for(int i = 0; i<word.length(); i++){
            if(word.charAt(i) == temp){
                stringBuilder.setCharAt(i,temp);
            }
        }
        return stringBuilder.toString();
    }



    //метод показа приветственного меню для начала игры или ее завершения
    public void startGameMenu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите " + NEW_GAME + ": Новая игра\n" + "Введите " + EXIT + ": Выйти из игры");

        while (true){
            String choice = scanner.nextLine();
            if(choice.equals(NEW_GAME)){
                break;
            } else if (choice.equals(EXIT)) {
                return;
            }else{
                System.out.println("Введите корректное значение: " + NEW_GAME + " или " + EXIT);
            }
        }
    }
}

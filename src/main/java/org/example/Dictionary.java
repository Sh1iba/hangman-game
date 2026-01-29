package main.java.org.example;
import java.nio.file.Path;
import java.util.*;

//должен получить с файла одно рандомное слово
public class Dictionary {
    private static ArrayList<String> dictionary;
    private static final String PATH = "src/main/resources/dictionary.txt";

    static {
        dictionary = readWordFromFile();
    }

    //метод считывания слова с файла
    private static ArrayList<String> readWordFromFile(){
        try{
            dictionary = new ArrayList<>();
            Scanner scanner = new Scanner(Path.of(PATH));
            while (scanner.hasNextLine()){
                dictionary.add(scanner.nextLine());
            }
            scanner.close();
            return dictionary;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //метод получения рандомного слова со словаря
    public static String getRandomWord(){
        return dictionary.get(new Random().nextInt(dictionary.size())).toLowerCase();
    }

    //метод получения скрытого слова
    public static String hideWord(String word) {
        return "_".repeat(word.length());
    }
}

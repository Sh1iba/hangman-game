package main.java.org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Dictionary {
    private static final String PATH = "src/main/resources/dictionary.txt";
    private static final List<String> dictionary = read();

    private static List<String> read(){
        try{
            List<String> words = new ArrayList<>();
            Scanner scanner = new Scanner(Path.of(PATH));
            while (scanner.hasNextLine()){
                words.add(scanner.nextLine());
            }
            scanner.close();
            return words;

        } catch (Exception e) {
            throw new RuntimeException("Не удалось найти файл по пути: " + PATH, e);
        }
    }

    public static String getRandomWord(){
        return dictionary.get(new Random().nextInt(dictionary.size())).toLowerCase();
    }

}

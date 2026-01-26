//должен получить с файла одно рандомное слово

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DictionaryReader {

    //метод считывания слова с файла
    public static String readWordFromFile(){
        try{
            Scanner scanner = new Scanner(new File("src/dictionary.txt"));
            ArrayList<String> dictionary = new ArrayList<>();
            while (scanner.hasNextLine()){
                dictionary.add(scanner.nextLine());
            }
            Random random = new Random();
            return dictionary.get(random.nextInt(dictionary.size())).toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //метод мб преобразования слова в *
}

//должен получить с файла одно рандомное слово
import java.io.File;
import java.util.*;

public class DictionaryReader {

    //метод считывания слова с файла
    public static String readWordFromFile(){
        try{
            Scanner scanner = new Scanner(new File("src/dictionary.txt"));
            ArrayList<String> dictionary = new ArrayList<>();
            while (scanner.hasNextLine()){
                dictionary.add(scanner.nextLine());
            }
            scanner.close();
            Random random = new Random();
            return dictionary.get(random.nextInt(dictionary.size())).toString().toLowerCase();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //метод преобразования слова в скрытое
    public static String hideWord(String word) {
        String hideWord = "";
        for(int i = 0; i < word.length(); i++){
            hideWord+="_";
        }
        return hideWord;
    }
}

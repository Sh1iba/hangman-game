package main.java.org.example;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class Dictionary {
    private static final String PATH = "src/main/resources/dictionary.txt";
    private static final List<String> dictionary;
    private static final Random RANDOM = new Random();

    static {
        try {
            dictionary = read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomWord() {
        return dictionary.get(RANDOM.nextInt(dictionary.size())).toLowerCase();
    }

    private static List<String> read() throws IOException {
        try {
            List<String> words = new ArrayList<>();
            Scanner scanner = new Scanner(Path.of(PATH));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();

            if (words.isEmpty()) {
                System.err.print("Ошибка: Файл со словарем пустой");
                throw new IOException("Пустой словарь");
            }
            return words;

        } catch (NoSuchFileException e) {
            System.err.printf("Ошибка: Не удалось найти файл по пути %s %n", PATH);
            throw new IOException("Не удалось найти файл", e);
        }
    }
}

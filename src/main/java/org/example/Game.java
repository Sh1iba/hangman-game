package main.java.org.example;

public class Game {
    private static final String LOSE_SPRITE = "\uD83D\uDEA9";
    private static final String WIN_SPRITE = "\uD83C\uDFC6";
    private static final int MAX_MISTAKES = GallowRenderer.getMaxPictures() - 1;

    private final ConsoleInput inputHandler = new ConsoleInput();
    private final UsedLetters usedLetters = new UsedLetters();
    private Word word;
    private int mistakeCount = 0;
    private GameStatus status;

    public void startGame() {
        while (true) {
            if (startGameMenu()) {
                startGameLoop();
            } else return;

        }
    }

    private void initRound() {
        status = GameStatus.IN_PROGRESS;
        usedLetters.clear();
        mistakeCount = 0;
        try {
            word = new Word(Dictionary.getRandomWord());
        } catch (RuntimeException e) {
            System.err.println("Завершение работы программы...");
        }
    }

    private void startGameLoop() {
        initRound();
        while (status == GameStatus.IN_PROGRESS) {
            printGameState();

            char letter = inputValidNotUsedLetter();

            if (word.containsLetter(letter)) {
                word.openLetter(letter);
            } else {
                mistakeCount++;
            }

            if (word.isWordGuessed()) {
                status = GameStatus.WIN;
                continue;
            }
            if (mistakeCount >= MAX_MISTAKES) {
                status = GameStatus.LOSE;
            }
        }
        switch (status) {
            case WIN:
                printGameState();
                System.out.printf("ВЫ ВЫИГРАЛИ %s%n", WIN_SPRITE);
                break;
            case LOSE:
                printGameState();
                System.out.printf("ВЫ ПРОИГРАЛИ %s%n", LOSE_SPRITE);
                System.out.println("Загаданное слово: " + word.getWord());
                break;

        }
    }

    private char inputValidNotUsedLetter() {
        while (true) {
            char letter = inputHandler.inputRussianLetter();
            if (usedLetters.contains(Character.toString(letter))) {
                System.out.println("Вы уже использовали эту букву");
                continue;
            }
            usedLetters.add(Character.toString(letter));
            return letter;
        }
    }

    private void printGameState() {
        try {
            GallowRenderer.render(mistakeCount);
        } catch (IllegalArgumentException e) {
            return;
        }
        System.out.println("Слово: " + word.getMaskedWord());
        System.out.println("Счетчик ошибок: " + mistakeCount);
        System.out.println("Использованные буквы: " + usedLetters.getSorted());
    }

    private boolean startGameMenu() {
        return inputHandler.readMenuChoice();
    }
}

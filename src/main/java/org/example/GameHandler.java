package main.java.org.example;

public class GameHandler {
    private static final String LOSE_SPRITE = "\uD83D\uDEA9";
    private static final String WIN_SPRITE = "\uD83C\uDFC6";
    private static final int MAX_MISTAKES = GallowRenderer.getMaxPictures();

    private final InputHandler inputHandler = new InputHandler();
    private WordHandler wordHandler;
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
        inputHandler.clearUsedLetters();
        mistakeCount = 0;
        try {
            wordHandler = new WordHandler(Dictionary.getRandomWord());
        } catch (RuntimeException e) {
            System.err.println("Завершение работы программы...");
            return;
        }
    }

    private void startGameLoop() {
        initRound();
        while (status == GameStatus.IN_PROGRESS) {
            printGameState();

            char letter = inputHandler.inputRussianLetter();
            boolean isLetterGuessed = wordHandler.guessLetter(letter);

            if(!isLetterGuessed){
                mistakeCount++;
            }

            if (wordHandler.isWordGuessed()) {
                status = GameStatus.WIN;
                continue;
            }
            if(mistakeCount >= MAX_MISTAKES){
                status = GameStatus.LOSE;
            }
        }
        switch (status){
            case WIN:
                printGameState();
                System.out.printf("ВЫ ВЫИГРАЛИ %s%n", WIN_SPRITE);
                break;
            case LOSE:
                printGameState();
                System.out.printf("ВЫ ПРОИГРАЛИ %s%n", LOSE_SPRITE);
                System.out.println("Загаданное слово: " + wordHandler.getWord());
                break;

        }
    }

    private void printGameState() {
        try {
            GallowRenderer.render(mistakeCount);
        }catch (IllegalArgumentException e) {
            return;
        }
        System.out.println("Слово: " + wordHandler.getMaskedWord());
        System.out.println("Счетчик ошибок: " + mistakeCount);
        System.out.println("Использованные буквы: " + inputHandler.getSortedUsedLetters());
    }

    private boolean startGameMenu() {
        return inputHandler.readMenuChoice();
    }
}

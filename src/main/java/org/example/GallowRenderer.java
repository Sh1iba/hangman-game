package main.java.org.example;

public class GallowRenderer {
    private static final String[] PICTURES = {
            """
         _______________
             |/      |
             |
             |
             |
             |
             |
         ____|____
        """,
            """
         _______________
             |/      |
             |       O
             |
             |
             |
             |
         ____|____
        """
            ,
            """
         _______________
             |/      |
             |       O
             |       |
             |
             |
             |
         ____|____
        """
            ,
            """
         _______________
             |/      |
             |       O
             |       |\\
             |
             |
             |
         ____|____
        """
            ,
            """
        _______________
             |/      |
             |       O
             |      /|\\
             |
             |
             |
         ____|____
       """
            ,
            """
        _______________
             |/      |
             |       O
             |      /|\\
             |      /
             |
             |
         ____|____
       """
            ,
            """
        _______________
             |/      |
             |       O
             |      /|\\
             |      / \\
             |
             |
         ____|____
       """
    };

    public static void render(int pictureNumber) {
        if (pictureNumber >= 0 && pictureNumber < PICTURES.length) {
            System.out.println(PICTURES[pictureNumber]);
        } else {
            throw new IllegalArgumentException("Недопустимое значение: " + pictureNumber);
        }
    }

    public static int getMaxPictures() {
        return PICTURES.length;
    }

}

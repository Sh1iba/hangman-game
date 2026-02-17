package main.java.org.example;

public enum Gallow {
    START_STATE("""
         _______________
             |/      |
             |
             |
             |
             |
             |
         ____|____
        """),
    MISTAKE_COUNT_ONE("""
         _______________
             |/      |
             |       O
             |
             |
             |
             |
         ____|____
        """
    ),
    MISTAKE_COUNT_TWO("""
         _______________
             |/      |
             |       O
             |       |
             |
             |
             |
         ____|____
        """
    ),
    MISTAKE_COUNT_THREE("""
         _______________
             |/      |
             |       O
             |       |\\
             |
             |
             |
         ____|____
        """
    ),
    MISTAKE_COUNT_FOUR("""
        _______________
             |/      |
             |       O
             |      /|\\
             |
             |
             |
         ____|____
       """
    ),
    MISTAKE_COUNT_FIVE("""
        _______________
             |/      |
             |       O
             |      /|\\
             |      /
             |
             |
         ____|____
       """
    ),
    FINAL_STATE("""
        _______________
             |/      |
             |       O
             |      /|\\
             |      / \\
             |
             |
         ____|____
       """
    ),;

    private String state;

    Gallow(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Gallow nextState(){
        Gallow[] gallows = values();
        int next = this.ordinal()+1;

        if(next >= gallows.length){
            return this;
        }
        return gallows[next];
    }

}

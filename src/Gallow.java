public enum Gallow {
    START_STATE("""
        -------
        -------
        ||   ||
             ||
             ||
             ||
             ||
             ||
        ===========
        ===========
        """),
    MISTAKE_COUNT_ONE("""
        -------
        -------
        |    ||
        O    ||
             ||
             ||
             ||
             ||
        ===========
        ===========
        """
    ),
    MISTAKE_COUNT_TWO("""
        -------
        -------
        |    ||
        O    ||
        |    ||
             ||
             ||
             ||
        ===========
        ===========
        """
    ),
    MISTAKE_COUNT_THREE("""
        -------
        -------
        |    ||
        O    ||
        |\\  ||
             ||
             ||
             ||
        ===========
        ===========
        """
    ),
    MISTAKE_COUNT_FOUR("""
        -------
        -------
        |    ||
        O    ||
       /|\\  ||
             ||
             ||
             ||
        ===========
        ===========
       """
    ),
    MISTAKE_COUNT_FIVE("""
        -------
        -------
        |    ||
        O    ||
       /|\\   ||
       /     ||
             ||
             ||
        ===========
        ===========
       """
    ),
    FINAL_STATE("""
        -------
        -------
        |    ||
        O    ||
       /|\\   ||
       / \\   ||
             ||
             ||
        ===========
        ===========
       """
    ),;

    private String state;

    Gallow(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    //метод для перехода на следующее состояние висилицы
    public void nextState(){
    }

}

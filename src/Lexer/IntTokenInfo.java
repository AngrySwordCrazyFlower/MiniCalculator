package Lexer;

public class IntTokenInfo extends TokenInfo {

    private int value;

    IntTokenInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

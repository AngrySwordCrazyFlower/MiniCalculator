package Lexer;

public class Token {

    public static final int OPERATION_LEFT_BRACKET = 0;
    public static final int OPERATION_RIGHT_BRACKET = 1;
    public static final int OPERATION_PLUS = 2;
    public static final int OPERATION_MINUS = 3;
    public static final int OPERATION_MULTIPLY = 4;
    public static final int OPERATION_DIVIDE = 5;
    public static final int NUMBER = 6;

    private String text;
    private int tokenType;
    
    private int row;
    private int column;

    static Token creator(String s, int tokenType, int row, int column) {
        return new Token(s, tokenType, row, column);
    }

    private Token(String s, int tokenType, int row, int column) {
        this.text = s;
        this.tokenType = tokenType;
        this.row = row;
        this.column = column;
    }

    public int getTokenType() {
        return tokenType;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("%-15s %d At (%d, %d)", text, tokenType, getRow(), getColumn());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

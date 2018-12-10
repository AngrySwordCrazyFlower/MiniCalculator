package Lexer;

public class Token {

    public static final int OPERATION_LEFT_BRACKET = 0;
    public static final int OPERATION_RIGHT_BRACKET = 1;
    public static final int OPERATION_PLUS = 2;
    public static final int OPERATION_MINUS = 3;
    public static final int OPERATION_MULTIPLY = 4;
    public static final int OPERATION_DIVIDE = 5;
    public static final int NUMBER_INT = 6;
    public static final int NUMBER_DOUBLE = 7;

    private String text;
    private int tokenType;
    
    private int row;
    private int column;

    private TokenInfo tokenInfo;

    static Token creator(String s, int tokenType, int row, int column) throws LexerException {
        TokenInfo tokenInfo = null;

        switch (tokenType) {
            case NUMBER_INT:
                try {
                    tokenInfo = new IntTokenInfo(Integer.parseInt(s));
                    break;
                } catch (NumberFormatException e) {
                    throw new LexerException(row, column, LexerException.INT_TOO_BIG);
                }
            case NUMBER_DOUBLE:
                try {
                    tokenInfo = new DoubleTokenInfo(Double.parseDouble(s));
                    break;
                } catch (NumberFormatException e) {
                    throw new LexerException(row, column, LexerException.DOUBLE_TOO_BIG);
                }
        }
        return new Token(s, tokenType, row, column, tokenInfo);
    }

    private Token(String s, int tokenType, int row, int column, TokenInfo tokenInfo) {
        this.text = s;
        this.tokenType = tokenType;
        this.row = row;
        this.column = column;
        this.tokenInfo = tokenInfo;
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

    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }
}

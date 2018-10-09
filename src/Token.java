public class Token {

    enum  TokenType {
        INTEGER,
        DOUBLE,
        VARIABLE,
        OPERATION_PLUS,
        OPERATION_MINUS,
        OPERATION_MULTIPLY,
        OPERATION_DIVIDE,
        OPERATION_LEFT_BRACKET,
        OPERATION_RIGHT_BRACKET,
        OPERATION_AND,
        OPERATION_OR
    }

    private String name;

    private TokenType tokenType;

    private int row;
    private int column;

    public static Token creator(String s, TokenType tokenType, int row, int column) {
        return new Token(s, tokenType, row, column);
    }

    private Token(String s, TokenType tokenType, int row, int column) {
        this.name = s;
        this.tokenType = tokenType;
        this.row = row;
        this.column = column;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "  " + tokenType.toString();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

package Lexer;

public class LexerException extends Exception {

    static private class LexerExceptionType {

        private int id;

        private String message;

        private LexerExceptionType(int id, String message) {
            this.id = id;
            this.message = message;
        }

    }

    public static final LexerExceptionType UNEXPECTED_CHAR = new LexerExceptionType(1001, "Unexpected char.");

    private int column;

    private int row;

    private LexerExceptionType lexerExceptionType;


    LexerException(int row, int column, LexerExceptionType lexerExceptionType) {
        this.row = row;
        this.column = column;
        this.lexerExceptionType = lexerExceptionType;
    }

    @Override
    public String toString() {
        return String.format("Error %d (%d, %d): %s", lexerExceptionType.id, row, column, lexerExceptionType.message);
    }

}

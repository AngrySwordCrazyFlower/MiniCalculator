package Lexer;

import java.util.ArrayList;
import java.util.List;

public class LexerScanner {

    private static class LexerReader {
        char[] s;

        int row;
        int column;
        int index;

        int length;
        private LexerReader(String s) {
            this.s = s.toCharArray();
            row = 1;
            column = 0;
            index = 0;
            length = s.length();
        }

        private char previewNextChar() {
            if (index >= length)
                return '\0';
            return s[index];
        }

        private char getNextChar() {
            if (index >= length)
                return '\0';
            char result = s[index];
            column++;
            if (result == '\n') {
                row++;
                column = 0;
            }
            index++;
            return result;
        }
    }

    public static List<Token> scan(String sentences) throws LexerException {

        LexerReader lexerReader = new LexerReader(sentences);

        List<Token> result = new ArrayList<>();
        Token temp;
        while (lexerReader.previewNextChar() != '\0') {
            temp = start(lexerReader);
            if (null != temp)
                result.add(temp);
        }
        return result;
    }

    private static Token start(LexerReader reader) throws LexerException {
        int startRow = reader.row;
        int startColumn = reader.column;
        char ch = reader.previewNextChar();
        StringBuffer text = new StringBuffer();
        if (Character.isDigit(ch)) {
            text.append(reader.getNextChar());
            return numberInteger(reader, text, startRow, startColumn);
        } else if (ch == '(') {
            text.append(reader.getNextChar());
            return operationLeftBracket(reader, text, startRow, startColumn);
        } else if (ch == ')') {
            text.append(reader.getNextChar());
            return operationRightBracket(reader, text, startRow, startColumn);
        } else if (ch == '+') {
            text.append(reader.getNextChar());
            return operationPlus(reader, text, startRow, startColumn);
        } else if (ch == '-') {
            text.append(reader.getNextChar());
            return operationMinus(reader, text, startRow, startColumn);
        } else if (ch == '*') {
            text.append(reader.getNextChar());
            return operationMultiply(reader, text, startRow, startColumn);
        } else if (ch == '/') {
            text.append(reader.getNextChar());
            return operationDivide(reader, text, startRow, startColumn);
        } else if (ch == '.') {
            text.append(reader.getNextChar());
            return numberDouble(reader, text, startRow, startColumn);
        } else if (ch == ' ' || ch == '\n' || ch == '\0') {
            reader.getNextChar();
            return null;
        }
        throw new LexerException(startRow, startColumn, LexerException.UNEXPECTED_CHAR);
    }

    private static Token operationLeftBracket(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_LEFT_BRACKET, startRow, startColumn);
    }

    private static Token operationRightBracket(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_RIGHT_BRACKET, startRow, startColumn);
    }

    private static Token operationPlus(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_PLUS, startRow, startColumn);
    }

    private static Token operationMinus(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_MINUS, startRow, startColumn);
    }

    private static Token operationDivide(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_DIVIDE, startRow, startColumn);
    }

    private static Token operationMultiply(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        return Token.creator(text.toString(), Token.OPERATION_MULTIPLY, startRow, startColumn);
    }

    private static Token numberInteger(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        char ch = reader.previewNextChar();
        if (Character.isDigit(ch)) {
            text.append(reader.getNextChar());
            return numberInteger(reader, text, startRow, startColumn);
        } else if (ch == '.') {
            text.append(reader.getNextChar());
            return numberDouble(reader, text, startRow, startColumn);
        } else
            return Token.creator(text.toString(), Token.NUMBER_INT, startRow, startColumn);
    }

    private static Token numberDouble(LexerReader reader, StringBuffer text, int startRow, int startColumn) throws LexerException {
        char ch = reader.previewNextChar();
        if (Character.isDigit(ch)) {
            text.append(reader.getNextChar());
            return numberDouble(reader, text, startRow, startColumn);
        } else {

            return Token.creator(text.toString(), Token.NUMBER_DOUBLE, startRow, startColumn);
        }
    }

}

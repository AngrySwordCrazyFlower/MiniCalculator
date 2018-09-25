import java.util.ArrayList;

public class TokenScanner {

    private String sentences;

    private int index;

    private int row;

    private int column;

    private StringBuilder builder;

    private CustomVariableTable customVariableTable;

    private ArrayList<Token> tokens;

    public TokenScanner(String sentences, CustomVariableTable customVariableTable) throws MyException {
        index = 0;
        row = 1;
        this.sentences = sentences;
        this.customVariableTable = customVariableTable;
        builder = new StringBuilder();
        tokens = new ArrayList<>();
        start(getNextChar());
    }

    private void generateToken(Token.TokenType tokenType) {
        String name = builder.toString();
        Token token = null;
        if (!customVariableTable.exists(name)) {
            token = Token.creator(name, tokenType);
            customVariableTable.putToken(name, Token.creator(name, tokenType));
        }
        else {
            token = customVariableTable.getTokenByName(name);
        }
        tokens.add(token);
        builder.setLength(0);
    }

    private char getNextChar() {
        index++;
        column++;
        if (index > sentences.length())
            return '\0';
        char c = sentences.charAt(index - 1);
        if ('\n' == c){
            row++;
            column = 1;
        }

        return c;
    }

    private void start(char ch) throws MyException {
        if (Character.isDigit(ch)) {
            builder.append(ch);
            state1(getNextChar());
        }
        else if (Character.isLetter(ch)) {
            builder.append(ch);
            state3(getNextChar());
        } else if (Util.SINGLE_OPERATION_COLLECTION.contains(Character.toString(ch))) {
            builder.append(ch);
            state4(getNextChar());
        } else if (Util.BLANK_OPERATION_COLLECTION.contains(Character.toString(ch))) {
            start(getNextChar());
        } else if (ch != '\0')
            throw new MyException(row,column, "Unexpected char");
    }

    private void state1(char ch) throws MyException {
        if (Character.isDigit(ch)) {
            builder.append(ch);
            state1(getNextChar());
        } else if ('.' == ch) {
            builder.append(ch);
            state2(getNextChar());
        } else {
            generateToken(Token.TokenType.INTEGER);
            start(ch);
        }
    }

    private void state2(char ch) throws MyException {
        if (Character.isDigit(ch)) {
            builder.append(ch);
            state2(getNextChar());
        } else {
            generateToken(Token.TokenType.DOUBLE);
            start(ch);
        }
    }

    private void state3(char ch) throws MyException {
        if (Character.isLetter(ch) || Character.isDigit(ch)) {
            builder.append(ch);
            state3(ch);
        } else {
            generateToken(Token.TokenType.VARIABLE);
            start(ch);
        }
    }

    private void state4(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION);
        start(ch);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

}

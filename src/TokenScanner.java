import java.util.ArrayList;

public class TokenScanner {

    private String sentences;

    private int index;

    private int row;

    private int column;

    private StringBuilder builder;

    private CustomVariableTable customVariableTable;

    private ArrayList<Token> tokens;

    private static String dealTokenName(String name, Token.TokenType tokenType) {

        int i = 0;
        if (tokenType == Token.TokenType.INTEGER) {
            for (; i < name.length(); i++)
                if (name.charAt(i) != '0')
                    break;
            return name.substring(Math.min(i, name.length() - 1));
        } else if (tokenType == Token.TokenType.DOUBLE) {
            for (; i < name.length(); i++)
                if (name.charAt(i) != '0')
                    break;
            int j = name.length() - 1;
            for (; j > -1; j--)
                if (name.charAt(j) != '0')
                    break;
            name = name.substring(i, j + 1);
            int index = name.indexOf('.');
            if (index == 0)
                name = "0" + name;
            else if (index == name.length() - 1)
                name = name.substring(0, name.length() - 1);
        }

        return name;
    }

    public TokenScanner(String sentences, CustomVariableTable customVariableTable) throws MyException {
        index = 0;
        row = 1;
        column = 0;
        this.sentences = sentences;
        this.customVariableTable = customVariableTable;
        builder = new StringBuilder();
        tokens = new ArrayList<>();
        start(getNextChar());
    }

    private void generateToken(Token.TokenType tokenType) {
        String name = builder.toString();
        int column = this.column - name.length();
        name = dealTokenName(name, tokenType);
        Token token = Token.creator(name, tokenType, row, column);
        tokens.add(token);
        builder.setLength(0);

        if (tokenType == Token.TokenType.INTEGER) {
            customVariableTable.put(name, new CustomInteger(Integer.valueOf(name)));
        } else if (tokenType == Token.TokenType.DOUBLE) {
            customVariableTable.put(name, new CustomDouble(Double.valueOf(name)));
        }

    }


    private char getNextChar() {
        index++;
        column++;
        if (index > sentences.length())
            return '\0';
        char c = sentences.charAt(index - 1);
        if ('\n' == c){
            row++;
            column = 0;
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
            switch (ch) {
                case '+':
                    state4(getNextChar());
                    break;
                case '-':
                    state5(getNextChar());
                    break;
                case '*':
                    state6(getNextChar());
                    break;
                case '/':
                    state7(getNextChar());
                    break;
                case '(':
                    state8(getNextChar());
                    break;
                case ')':
                    state9(getNextChar());
                    break;
                case '&':
                    state10(getNextChar());
                    break;
                case '|':
                    state11(getNextChar());
                    break;
            }
        } else if (Util.BLANK_OPERATION_COLLECTION.contains(Character.toString(ch))) {
            start(getNextChar());
        } else if (ch != '\0')
            throw new MyException(row, column, "Unexpected char");
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
            state3(getNextChar());
        } else {
            generateToken(Token.TokenType.VARIABLE);
            start(ch);
        }
    }

    private void state4(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_PLUS);
        start(ch);
    }

    private void state5(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_MINUS);
        start(ch);
    }

    private void state6(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_MULTIPLY);
        start(ch);
    }

    private void state7(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_DIVIDE);
        start(ch);
    }

    private void state8(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_LEFT_BRACKET);
        start(ch);
    }

    private void state9(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_RIGHT_BRACKET);
        start(ch);
    }

    private void state10(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_AND);
        start(ch);
    }

    private void state11(char ch) throws MyException {
        generateToken(Token.TokenType.OPERATION_OR);
        start(ch);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

}

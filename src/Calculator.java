import java.util.ArrayList;
import java.util.Stack;


public class Calculator {

//    public static double getResult(ArrayList<Token> tokens, CustomVariableTable.Scope scope) {
    public static Token getResult(ArrayList<Token> tokens, CustomVariableTable variableTable) throws MyException {

        Stack<Token> generateToken = new Stack<>();
        for (Token tmp : tokens) {
            Token.TokenType type = tmp.getTokenType();

            try {
                switch (type) {
                    case OPERATION_PLUS:
                        add(generateToken);
                        break;
                    case OPERATION_DIVIDE:
                        divide(generateToken);
                        break;
                    case OPERATION_MINUS:
                        minus(generateToken);
                        break;
                    case OPERATION_MULTIPLY:
                        multiply(generateToken);
                        break;
                    default:
                        generateToken.add(tmp);
                }
            } catch (StackNullException e) {
                throw new MyException(tmp.getRow(), tmp.getColumn(), "Need number before this operation.");
            }

        }

        if (generateToken.size() != 1) {
            Token token = tokens.get(tokens.size() - 1);
            throw new MyException(token.getRow(), token.getColumn(), "Need operation after this number.");
        }
        return generateToken.peek();
    }

    private static void add(Stack<Token> stack) throws StackNullException {
        Token token1 = getTokenFromTop(stack);
        Token token2 = getTokenFromTop(stack);

        Token.TokenType type = judgeType(token1, token2);

        if (type == Token.TokenType.INTEGER) {
            int intResult = Integer.parseInt(token1.getName()) + Integer.parseInt(token2.getName());
            stack.add(createToken(intResult, token1.getRow(), token1.getColumn()));
        } else {
            double doubleResult = Double.parseDouble(token1.getName()) + Double.parseDouble(token2.getName());
            stack.add(createToken(doubleResult, token1.getRow(), token1.getColumn()));
        }
    }

    private static void divide(Stack<Token> stack) throws StackNullException {
        Token token1 = getTokenFromTop(stack);
        Token token2 = getTokenFromTop(stack);

        Token.TokenType type = judgeType(token1, token2);

        if (type == Token.TokenType.INTEGER) {
            int intResult = Integer.parseInt(token1.getName()) / Integer.parseInt(token2.getName());
            stack.add(createToken(intResult, token1.getRow(), token1.getColumn()));
        } else {
            double doubleResult = Double.parseDouble(token1.getName()) / Double.parseDouble(token2.getName());
            stack.add(createToken(doubleResult, token1.getRow(), token1.getColumn()));
        }
    }

    private static void minus(Stack<Token> stack) throws StackNullException {
        Token token1 = getTokenFromTop(stack);
        Token token2 = getTokenFromTop(stack);

        Token.TokenType type = judgeType(token1, token2);

        if (type == Token.TokenType.INTEGER) {
            int intResult = Integer.parseInt(token1.getName()) - Integer.parseInt(token2.getName());
            stack.add(createToken(intResult, token1.getRow(), token1.getColumn()));
        } else {
            double doubleResult = Double.parseDouble(token1.getName()) - Double.parseDouble(token2.getName());
            stack.add(createToken(doubleResult, token1.getRow(), token1.getColumn()));
        }
    }

    private static void multiply(Stack<Token> stack) throws StackNullException {
        Token token1 = getTokenFromTop(stack);
        Token token2 = getTokenFromTop(stack);

        Token.TokenType type = judgeType(token1, token2);

        if (type == Token.TokenType.INTEGER) {
            int intResult = Integer.parseInt(token1.getName()) * Integer.parseInt(token2.getName());
            stack.add(createToken(intResult, token1.getRow(), token1.getColumn()));
        } else {
            double doubleResult = Double.parseDouble(token1.getName()) * Double.parseDouble(token2.getName());
            stack.add(createToken(doubleResult, token1.getRow(), token1.getColumn()));
        }
    }

    private static Token.TokenType judgeType(Token a, Token b) {
        if (a.getTokenType() == Token.TokenType.DOUBLE || b.getTokenType() == Token.TokenType.DOUBLE)
            return Token.TokenType.DOUBLE;
        return Token.TokenType.INTEGER;
    }

    private static Token getTokenFromTop(Stack<Token> stack) throws StackNullException {
        if (!stack.isEmpty())
            return stack.pop();
        throw new StackNullException();
    }

    private static Token createToken(double d, int row, int column) {
        return Token.creator(String.valueOf(d), Token.TokenType.DOUBLE, row, column);
    }

    private static Token createToken(int i, int row, int column) {
        return Token.creator(String.valueOf(i), Token.TokenType.INTEGER, row, column);
    }



    private static class StackNullException extends Exception {

    }
}

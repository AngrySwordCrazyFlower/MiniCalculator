
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
public class SuffixConvertMachine {
    public final static HashMap<Token.TokenType,Integer> priorityTable;

    static{
        priorityTable = new HashMap<>();
        priorityTable.put(Token.TokenType.OPERATION_PLUS, 10);
        priorityTable.put(Token.TokenType.OPERATION_MINUS, 10);
        priorityTable.put(Token.TokenType.OPERATION_MULTIPLY, 20);
        priorityTable.put(Token.TokenType.OPERATION_DIVIDE, 20);
        priorityTable.put(Token.TokenType.OPERATION_LEFT_BRACKET, 30);
        priorityTable.put(Token.TokenType.OPERATION_RIGHT_BRACKET, 30);
    }

    public static int getPriority(Token token){
        return priorityTable.get(token.getTokenType());
    }

    public static ArrayList<Token> Convert(ArrayList<Token> infixTokens) throws MyException {
        ArrayList<Token> suffix = new ArrayList<>();
        Stack<Token> stk = new Stack<>();
        boolean positive = true;
        boolean preOp = false;
        for (Token token : infixTokens) {

            switch (token.getTokenType()) {


                case OPERATION_MINUS:
                case OPERATION_PLUS:
                    if (preOp) {
                        if (token.getTokenType() == Token.TokenType.OPERATION_MINUS)
                            positive = !positive;
                        break;
                    }
                    else preOp = true;
                case OPERATION_DIVIDE:
                case OPERATION_MULTIPLY:
                    while (!stk.isEmpty() && getPriority(stk.peek()) >= getPriority(token) && stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET)
                        suffix.add(stk.pop());
                case OPERATION_LEFT_BRACKET:
                    stk.push(token);
                    break;
                case DOUBLE:
                case INTEGER:
                    if (!positive)
                        token.toNegative();
                    suffix.add(token);
                    positive = true;
                    preOp = false;
                    break;

                case OPERATION_RIGHT_BRACKET:
                    while (!stk.isEmpty() && stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET)
                        suffix.add(stk.pop());
                    //弹出左括号
                    if (!stk.isEmpty())
                        stk.pop();
                    else
                        throw new MyException(token.getRow(), token.getColumn(), "An extra right bracket");
                    break;
            }

        }

        while (!stk.isEmpty())
            suffix.add(stk.pop());


        return suffix;
    }

}
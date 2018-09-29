
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
public class SuffixConvertMachine {
    public final static HashMap<Token.TokenType,Integer> priorityTable = null;

    static{
        priorityTable.put(Token.TokenType.OPERATION_PLUS,10);
        priorityTable.put(Token.TokenType.OPERATION_MINUS,10);
        priorityTable.put(Token.TokenType.OPERATION_MULTIPLY,20);
        priorityTable.put(Token.TokenType.OPERATION_DIVIDE,20);
        priorityTable.put(Token.TokenType.OPERATION_LEFT_BRACKET,30);
        priorityTable.put(Token.TokenType.OPERATION_RIGHT_BRACKET,30);
    }

    public static int getPriority(Token token){
        return priorityTable.get(token.getTokenType());
    }

    public static ArrayList<Token> Convert(ArrayList<Token> infixTokens) {
        ArrayList<Token> suffix = new ArrayList<>();
        Stack<Token> stk = new Stack<>();
        for (Token token : infixTokens) {
            Token topToken = stk.peek();
            if(topToken != null){
                if (token.getTokenType() == Token.TokenType.OPERATION_PLUS ||
                        token.getTokenType() == Token.TokenType.OPERATION_MINUS ||
                        token.getTokenType() == Token.TokenType.OPERATION_MULTIPLY ||
                        token.getTokenType() == Token.TokenType.OPERATION_DIVIDE){
                    if(getPriority(topToken) < getPriority(token))
                        stk.push(token);
                    else{
                        if(stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET){
                            suffix.add(stk.peek());
                        }
                        stk.pop();
                        stk.push(token);
                    }
                }
                else if(token.getTokenType() == Token.TokenType.OPERATION_LEFT_BRACKET){
                    stk.push(token);
                }
                else if(token.getTokenType() == Token.TokenType.OPERATION_RIGHT_BRACKET){
                    while(stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET){
                        suffix.add(stk.peek());
                        stk.pop();
                    }
                }
                else if (token.getTokenType() == Token.TokenType.INTEGER ||
                        token.getTokenType() == Token.TokenType.DOUBLE ||
                        token.getTokenType() == Token.TokenType.VARIABLE){
                    suffix.add(token);
                }
            }

        }

        return suffix;
    }

}
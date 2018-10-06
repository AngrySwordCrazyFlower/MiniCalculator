
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
public class SuffixConvertMachine {
    public final static HashMap<Token.TokenType,Integer> priorityTable;

    static{
        priorityTable = new HashMap<>();
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
            if (token.getTokenType() == Token.TokenType.OPERATION_PLUS ||
                    token.getTokenType() == Token.TokenType.OPERATION_MINUS ||
                    token.getTokenType() == Token.TokenType.OPERATION_MULTIPLY ||
                    token.getTokenType() == Token.TokenType.OPERATION_DIVIDE){
                if(stk.size() == 0){
                    stk.push(token);
                    continue;
                }

                else if(getPriority(stk.peek()) < getPriority(token) || stk.peek().getTokenType() == Token.TokenType.OPERATION_LEFT_BRACKET){
                    stk.push(token);
                    continue;
                }


                while(getPriority(stk.peek()) >= getPriority(token) && stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET){

                    suffix.add(stk.peek());

                    stk.pop();
                    if(stk.size() == 0)
                        break;
                }
                stk.push(token);





            }
            else if(token.getTokenType() == Token.TokenType.OPERATION_LEFT_BRACKET){
                stk.push(token);
            }
            else if(token.getTokenType() == Token.TokenType.OPERATION_RIGHT_BRACKET){
                while(stk.peek().getTokenType() != Token.TokenType.OPERATION_LEFT_BRACKET){
                    suffix.add(stk.peek());
                    stk.pop();
                }
                stk.pop();
            }
            else if (token.getTokenType() == Token.TokenType.INTEGER ||
                    token.getTokenType() == Token.TokenType.DOUBLE ||
                    token.getTokenType() == Token.TokenType.VARIABLE){
                suffix.add(token);
            }
        }

        while(!stk.isEmpty()){
            suffix.add(stk.peek());
            stk.pop();
        }


        return suffix;
    }

}
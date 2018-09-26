import java.util.ArrayList;
import java.util.Stack;
public class SuffixConvertMachine {

    public static ArrayList<Token> Convert(ArrayList<Token> infixTokens){
        ArrayList<Token> suffix = new ArrayList<>();
        Stack<Token> stk = new Stack<>();
        for(Token token : infixTokens){
            if(token.getTokenType() == Token.TokenType.OPERATION){
                stk.push(token);
            }
            else if()
        }

        return null;
    }
}

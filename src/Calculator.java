import java.util.ArrayList;
import java.util.Stack;


public class Calculator {

//    public static double getResult(ArrayList<Token> tokens, CustomVariableTable.Scope scope){
    public static double getResult(ArrayList<Token> tokens){
        Stack<java.io.Serializable> st = new Stack<>();

        for (Token tmp : tokens) {

            Token.TokenType tmpType = tmp.getTokenType();
            if (tmp.getTokenType() == Token.TokenType.INTEGER || tmp.getTokenType() == Token.TokenType.DOUBLE) {
                st.push(tmp.getName());
            } else {
                /*
                switch (tmp.getTokenType()){
                    case Token.TokenType.OPERATION_DIVIDE:
                }*/
                double result = 0;

                if (tmpType == Token.TokenType.OPERATION_PLUS) {
                    result = getStNext(st) + getStNext(st);
                }

                if (tmpType == Token.TokenType.OPERATION_MINUS) {
                    double right = getStNext(st);
                    result = getStNext(st) - right;
                }

                if (tmpType == Token.TokenType.OPERATION_MULTIPLY) {
                    result = getStNext(st) * getStNext(st);
                }

                // TODO Exception handling needed
                if (tmpType == Token.TokenType.OPERATION_DIVIDE) {
                    double right = getStNext(st);

                    if (right == 0) break;
                    result = getStNext(st) / right;
                }

                st.push(result);

            }

        }

        return getStNext(st);
    }

    private static double getStNext(Stack<java.io.Serializable> stack){
        return Double.valueOf(stack.pop().toString()) ;
    }

}

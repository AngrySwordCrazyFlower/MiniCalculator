import java.util.ArrayList;

public class Test {

    //for test
    public static void main(String[] args) {
        try {
            TokenScanner scanner = new TokenScanner("a+2 * 3.1", CustomVariableTable.GLOBAL_VARIABLE_TABLE);
            ArrayList<Token> tokens = scanner.getTokens();
            for (Token token : tokens) {
                System.out.println(token.getName());
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}

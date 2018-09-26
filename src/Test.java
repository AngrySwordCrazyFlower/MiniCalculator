import java.util.ArrayList;

public class Test {

    //for test
    public static void main(String[] args) {
        try {

            TokenScanner scanner = new TokenScanner("a + b*c + (d * e + f) * g", CustomVariableTable.GLOBAL_VARIABLE_TABLE);

            ArrayList<Token> tokens = scanner.getTokens();

            for (Token token : tokens) {
                System.out.println(token.getName());
            }

            ArrayList<Token> suffixTokens = SuffixConvertMachine.Convert(tokens);
            for (Token token : suffixTokens) {
                System.out.println(token.getName());
            }

        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}

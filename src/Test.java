import java.util.ArrayList;

public class Test {

    //for test
    public static void main(String[] args) {
        try {
            TokenScanner scanner = new TokenScanner("(1+2. * a1) + 3", CustomVariableTable.GLOBAL_VARIABLE_TABLE);
            ArrayList<Token> tokens = scanner.getTokens();

            tokens = SuffixConvertMachine.Convert(tokens);

            for (Token token : tokens) {
                System.out.println(token.getName());




            }

        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}

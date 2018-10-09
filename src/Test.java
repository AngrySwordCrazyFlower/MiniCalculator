import java.util.ArrayList;

import static java.lang.System.out;


public class Test {

    //for test
    public static void main(String[] args) {
        try {
            TokenScanner scanner = new TokenScanner("(1+2. * 5.8) + 3", CustomVariableTable.GLOBAL_VARIABLE_TABLE);
            ArrayList<Token> tokens = scanner.getTokens();

            tokens = SuffixConvertMachine.Convert(tokens);

            for (Token token : tokens) {
                out.println(token.getName());
            }

            out.println(Calculator.getResult(tokens));



        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}

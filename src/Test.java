import java.util.ArrayList;

import static java.lang.System.out;


public class Test {

    //for test
    public static void main(String[] args) {
        try {



            //正确  -7.6
            //TokenScanner scanner = new TokenScanner("(1 + - 2. * 5.8) + 3");

            //错误  多右括号
            //TokenScanner scanner = new TokenScanner("(1 + - 2.) * 5.8) + 3");

            //错误 少右括号
            //TokenScanner scanner = new TokenScanner("(((1 + - 2.) * 5.8) + 3");

            //错误 2个乘号
            //TokenScanner scanner = new TokenScanner("((1 + - 2.) ** 5.8) + 3");

            //错误 多了个a
            //TokenScanner scanner = new TokenScanner("((1 + - .2a) * 5.80) + 3");

            //正确 7.64
            //TokenScanner scanner = new TokenScanner("((1 + - .2) * 5.80) + 3");

            //错误  最前面需要乘号
            //TokenScanner scanner = new TokenScanner("* 1 - + 2     3");

            //错误  最前面需要乘号
            TokenScanner scanner = new TokenScanner("1 ()");

            ArrayList<Token> tokens = scanner.getTokens();

            for (Token token : tokens)
                out.println(token.getName());

            tokens = SuffixConvertMachine.Convert(tokens);


            out.println(Calculator.getResult(tokens));

        } catch (MyException e) {
            System.out.println(e);
        }
    }

}

import MyParser.Grammar;
import MyParser.ParserException;

public class Test {

    //for test
    public static void main(String[] args) {
        try {

            MyParser.Grammar grammar = new Grammar("S", "+ - * / ( ) i", "E N T F",
                    new String[] {
                            "E -> - E | + E | N",
                            "N -> N + T | N - T | T",
                            "T -> T * F | T / F | F",
                            "F -> ( E ) | i"
                    });

            System.out.println(grammar);


        }catch (ParserException e) {
            e.printStackTrace();
        }
    }

}

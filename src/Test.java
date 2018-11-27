import Lexer.LexerException;
import Lexer.LexerScanner;
import Lexer.Token;
import MyParser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    //for test
    public static void main(String[] args) {
        try {

            MyParser.Grammar grammar = new Grammar("N", "+ - * / ( ) i", "N T E",
                    new String[] {
                            "N -> N + T | N - T | T",
                            "T -> T * E | T / E | E",
                            "E -> - E | + E | ( N ) | i"
                    });

            Map<String, TerminalElement> map = grammar.getTerminalElementMap();
            List<Node> nodeList = new ArrayList<>();

            List<Token> tokens = LexerScanner.scan("-1+2*+++----(1+3)");

            for (Token token : tokens) {
                switch (token.getTokenType()) {
                    case Token.NUMBER:
                        nodeList.add(new Node(map.get("i")));
                        break;
                    case Token.OPERATION_PLUS:
                        nodeList.add(new Node(map.get("+")));
                        break;
                    case Token.OPERATION_MINUS:
                        nodeList.add(new Node(map.get("-")));
                        break;
                    case Token.OPERATION_MULTIPLY:
                        nodeList.add(new Node(map.get("*")));
                        break;
                    case Token.OPERATION_DIVIDE:
                        nodeList.add(new Node(map.get("/")));
                        break;
                    case Token.OPERATION_LEFT_BRACKET:
                        nodeList.add(new Node(map.get("(")));
                        break;
                    case Token.OPERATION_RIGHT_BRACKET:
                        nodeList.add(new Node(map.get(")")));
                        break;
                }
            }

            Node node = grammar.parse(nodeList);

            System.out.println(node.toString());

        }catch (ParserException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        }
    }

}

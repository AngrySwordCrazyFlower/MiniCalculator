package GUI;

import Lexer.LexerException;
import Lexer.LexerScanner;
import Lexer.Token;
import MyParser.Grammar;
import MyParser.Node;
import MyParser.ParserException;
import MyParser.TerminalElement;
import javafx.animation.KeyValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application{

    private static int width = 800, height = 600;
    private static int radius = 20;
    private static int distance = 15;
    private static int verticalDistance = 50;
    private static Font textFont = Font.font(20);

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            MyParser.Grammar grammar = new Grammar("N", "+ - * / ( ) i", "N T E",
                    new String[] {
                            "N -> N + T | N - T | T",
                            "T -> T * E | T / E | E",
                            "E -> - E | + E | ( N ) | i"
                    });

            Map<String, TerminalElement> map = grammar.getTerminalElementMap();
            List<Node> nodeList = new ArrayList<>();

            List<Token> tokens = LexerScanner.scan("5 5");

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

            StackPane stackPane = new StackPane();
            Scene scene = new Scene(stackPane, width, height);
            drawTree(stackPane, node);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (ParserException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        }
    }

    public void drawTree(StackPane stackPane, Node node) {
        Map<Node, Integer> nodeWidthMap = new HashMap<>();

        calculateWidth(nodeWidthMap, node);

        drawNode(stackPane, node, 0, radius, nodeWidthMap, 0, 0);

//        Circle circle = new Circle(radius, Color.rgb(255, 153, 0, 1.0));
//        circle.setTranslateY(-height / 2 + radius);
//        Text text = new Text("E");
//        text.setFont(Font.font(20.0));
//        text.setTranslateY(-height / 2 + radius);
//
//        stackPane.getChildren().add(circle);
//        stackPane.getChildren().add(text);
    }

    public void drawNode(StackPane stackPane, Node node, int left, int high, Map<Node, Integer> map, int parentX, int parentY) {


        int x = -width / 2 + left + map.get(node) / 2;
        int y = - height / 2 + high;

        if (high != radius) {
            Line line = new Line(x, y, parentX, parentY);
            line.setTranslateX((x + parentX) / 2);
            line.setTranslateY((y + parentY) / 2);
            stackPane.getChildren().add(line);
        }

        Circle circle = new Circle(radius, Color.rgb(255, 153, 0, 1.0));
        circle.setTranslateX(x);
        circle.setTranslateY(y);

        Text text = new Text(node.getAbstractElement().getText());
        text.setFont(textFont);
        text.setTranslateX(x);
        text.setTranslateY(y);

        int tempLeft = 0;
        for (Node son : node.getSons()) {
            drawNode(stackPane, son, left + tempLeft, high + verticalDistance, map, x, y);
            tempLeft += map.get(son) + distance;
        }
        stackPane.getChildren().add(circle);
        stackPane.getChildren().add(text);
    }


    public void calculateWidth(Map<Node, Integer> map, Node parent) {

        List<Node> sons = parent.getSons();
        if (sons.size() == 0) {
            map.put(parent, radius * 2);
            return;
        }

        int width = 0;

        for (Node son : parent.getSons()) {
            calculateWidth(map, son);
            width += map.get(son) + distance;
        }

        map.put(parent, width - distance);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
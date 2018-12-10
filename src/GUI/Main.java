package GUI;

import Lexer.*;
import Parser.*;
import Parser.Node.DoubleNodeInfo;
import Parser.Node.IntNodeInfo;
import Parser.Node.Node;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.regex.Pattern;

public class Main extends Application{

    private static int radius = 20;
    private static int distance = 15;
    private static int verticalDistance = 50;

    private Font drawTextFont = Font.font(20);
    private Font inputTextFont = Font.font(36);

    private Font buttonTextFont = Font.font(25);

    private boolean keyControl = false;
    private boolean keyV = false;
    private static Pattern pattern1 = Pattern.compile("[0-9+\\-*/().。]*");
    private static Pattern pattern2 = Pattern.compile("[0-9+\\-*/().。]");

    private Background defaultButtonBackground = new Background(new BackgroundFill(Color.rgb(230, 230, 230, 1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background mouseOnButtonBackground = new Background(new BackgroundFill(Color.rgb(212, 212, 212, 1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background mousePressedButtonBackground = new Background(new BackgroundFill(Color.rgb(196, 196, 196, 1), CornerRadii.EMPTY, Insets.EMPTY));

    private Parser.Grammar grammar;

    private Label inputLabel;
    private String content;

    public Main() throws ParserException {
        grammar = new Grammar("E", "+ - * / ( ) i", "E T F",
            new String[] {
                    "E -> E + T",
                    "E -> E - T",
                    "E -> T",
                    "T -> T * F",
                    "T -> T / F",
                    "T -> F",
                    "F -> + F",
                    "F -> - F",
                    "F -> ( E )",
                    "F -> i"
            });
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println(grammar);
        content = "";
        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setPadding(new Insets(2, 0, 2, 0));
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        inputLabel = new Label();
        inputLabel.prefWidthProperty().bind(root.widthProperty().subtract(20));
        inputLabel.prefHeightProperty().bind(root.heightProperty().multiply(0.24));
        inputLabel.setBorder(null);
        inputLabel.setBackground(
                new Background(new BackgroundFill(
                        Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        inputLabel.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        inputLabel.setFont(inputTextFont);
        VBox.setMargin(inputLabel, new Insets(0, 10, 0, 10));
        VBox.setMargin(inputLabel, new Insets(2));
        root.getChildren().add(inputLabel);

        Button button;
        HBox hBox;
        for (int i = 0; i < 4; i++) {
            hBox = new HBox();
            hBox.setPadding(new Insets(2, 3, 2, 3));
            hBox.prefHeightProperty().bind(scene.heightProperty().multiply(0.2));
            for (int j = 0; j < 5; j++) {
                button = createButton();
                button.prefWidthProperty().bind(hBox.widthProperty().subtract(18).multiply(0.2));
                button.prefHeightProperty().bind(hBox.heightProperty().subtract(4));
                switch (i * 5 + j) {
                    case 0:
                        button.setText("7");
                        button.setOnMouseClicked(event -> {
                            content = content + "7";
                            inputLabel.setText(content);
                        });
                        break;
                    case 1:
                        button.setText("8");
                        button.setOnMouseClicked(event -> {
                            content = content + "8";
                            inputLabel.setText(content);
                        });
                        break;
                    case 2:
                        button.setText("9");
                        button.setOnMouseClicked(event -> {
                            content = content + "9";
                            inputLabel.setText(content);
                        });
                        break;
                    case 3:
                        button.setText("C");
                        button.setOnMouseClicked(event -> {
                            content = "";
                            inputLabel.setText(content);
                        });
                        break;
                    case 4:
                        button.setText("D");
                        button.setOnMouseClicked(event -> {
                            if (content.length() > 0) {
                                content = content.substring(0, content.length() - 1);
                                inputLabel.setText(content);
                            }
                        });
                        break;
                    case 5:
                        button.setText("4");
                        button.setOnMouseClicked(event -> {
                            content = content + "4";
                            inputLabel.setText(content);
                        });
                        break;
                    case 6:
                        button.setText("5");
                        button.setOnMouseClicked(event -> {
                            content = content + "5";
                            inputLabel.setText(content);
                        });
                        break;
                    case 7:
                        button.setText("6");
                        button.setOnMouseClicked(event -> {
                            content = content + "6";
                            inputLabel.setText(content);
                        });
                        break;
                    case 8:
                        button.setText("+");
                        button.setOnMouseClicked(event -> {
                            content = content + "+";
                            inputLabel.setText(content);
                        });
                        break;
                    case 9:
                        button.setText("-");
                        button.setOnMouseClicked(event -> {
                            content = content + "-";
                            inputLabel.setText(content);
                        });
                        break;
                    case 10:
                        button.setText("1");
                        button.setOnMouseClicked(event -> {
                            content = content + "1";
                            inputLabel.setText(content);
                        });
                        break;
                    case 11:
                        button.setText("2");
                        button.setOnMouseClicked(event -> {
                            content = content + "2";
                            inputLabel.setText(content);
                        });
                        break;
                    case 12:
                        button.setText("3");
                        button.setOnMouseClicked(event -> {
                            content = content + "3";
                            inputLabel.setText(content);
                        });
                        break;
                    case 13:
                        button.setText("*");
                        button.setOnMouseClicked(event -> {
                            content = content + "*";
                            inputLabel.setText(content);
                        });
                        break;
                    case 14:
                        button.setText("/");
                        button.setOnMouseClicked(event -> {
                            content = content + "/";
                            inputLabel.setText(content);
                        });
                        break;
                    case 15:
                        button.setText(".");
                        button.setOnMouseClicked(event -> {
                            content = content + ".";
                            inputLabel.setText(content);
                        });
                        break;
                    case 16:
                        button.setText("0");
                        button.setOnMouseClicked(event -> {
                            content = content + "0";
                            inputLabel.setText(content);
                        });
                        break;
                    case 17:
                        button.setText("=");
                        //calculate
                        button.setOnMouseClicked(event -> calculate(content));
                        break;
                    case 18:
                        button.setText("(");
                        button.setOnMouseClicked(event -> {
                            content = content + "(";
                            inputLabel.setText(content);
                        });
                        break;
                    case 19:
                        button.setText(")");
                        button.setOnMouseClicked(event -> {
                            content = content + ")";
                            inputLabel.setText(content);
                        });
                        break;
                }
                hBox.getChildren().add(button);
                HBox.setMargin(button, new Insets(0, 1, 0, 1));
            }
            root.getChildren().add(hBox);
        }

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                    if (content.length() > 0) {
                        content = content.substring(0, content.length() - 1);
                        inputLabel.setText(content);
                    }
                }
                if (event.getCode().equals(KeyCode.CONTROL))
                    keyControl = true;
                if (event.getCode().equals(KeyCode.V))
                    keyV = true;
                if (keyControl && keyV) {
                    String content;
                    content = Clipboard.getSystemClipboard().getString();
                    if (pattern1.matcher(content).matches()) {
                        Main.this.content += content.replaceAll("。", ".");
                        inputLabel.setText(Main.this.content);
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.CONTROL))
                    keyControl = false;
                if (event.getCode().equals(KeyCode.V))
                    keyV = false;
            }
        });

        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String text = event.getCharacter();

                if (text.equals("=")) {
                    calculate(content);
                    return;
                }

                if (pattern2.matcher(text).matches()) {
                    content += text.replaceAll("。", ".");
                    inputLabel.setText(content);
                }
            }
        });
        primaryStage.setMinWidth(480);
        primaryStage.setMinHeight(480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton() {
        Button button = new Button();
        button.setBackground(defaultButtonBackground);

        button.setOnMouseEntered(event -> {
            if (button.isPressed()) {
                button.setBackground(mousePressedButtonBackground);
            } else {
                button.setBackground(mouseOnButtonBackground);
            }
            button.setBorder(new Border(new BorderStroke(Color.rgb(175, 175, 175), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        });
        button.setOnMouseExited(event -> {
            button.setBackground(defaultButtonBackground);
            button.setBorder(null);
        });
        button.setOnMousePressed(event -> {
            button.setBackground(mousePressedButtonBackground);
        });
        button.setOnMouseReleased(event -> {
            if (!button.isHover())
                button.setBackground(defaultButtonBackground);
            else
                button.setBackground(mouseOnButtonBackground);
        });
        button.setBorder(null);
        button.setFont(buttonTextFont);
        return button;
    }

    public void drawTree(Pane pane, Node node) {
        Text text = new Text();
        text.setText(content);
        text.setTranslateX(5);
        text.setTranslateY(text.getBaselineOffset() + 2);
        pane.getChildren().add(text);
        Map<Node, Integer> nodeWidthMap = new HashMap<>();

        calculateWidth(nodeWidthMap, node);

        drawNode(pane, node, 0, radius, nodeWidthMap, 0, 0);
    }

    public void drawNode(Pane pane, Node node, int left, int high, Map<Node, Integer> map, int parentX, int parentY) {


        int x = left + map.get(node) / 2;
        int y = high;

        if (high != radius) {
            Line line = new Line(x, y, parentX, parentY);
            pane.getChildren().add(line);
        }

        Circle circle = new Circle(radius, Color.rgb(255, 153, 0, 1.0));
        circle.setTranslateX(x);
        circle.setTranslateY(y);

        Text text = new Text(node.getAbstractElement().getText());
        text.setFont(drawTextFont);
        text.setTranslateX(x - text.getLayoutBounds().getWidth() / 2);
        text.setTranslateY(y + (text.getBaselineOffset()  * 2 - text.getLayoutBounds().getHeight()) / 2);

        int tempLeft = 0;
        for (Node son : node.getSons()) {
            drawNode(pane, son, left + tempLeft, high + verticalDistance, map, x, y);
            tempLeft += map.get(son) + distance;
        }
        pane.getChildren().add(circle);
        pane.getChildren().add(text);
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

    private void calculate(String value) {
        Node node;
        Map<String, TerminalElement> map;
        List<Node> nodeList;
        List<Token> tokens;
        Stage stage;
        Pane pane;
        try {
            map = grammar.getTerminalElementMap();
            nodeList = new ArrayList<>();

            tokens = LexerScanner.scan(value);

            for (Token token : tokens) {
                switch (token.getTokenType()) {
                    case Token.NUMBER_DOUBLE:
                        nodeList.add(new Node(map.get("i"), new DoubleNodeInfo(((DoubleTokenInfo) token.getTokenInfo()).getValue())));
                        break;
                    case Token.NUMBER_INT:
                        nodeList.add(new Node(map.get("i"), new IntNodeInfo(((IntTokenInfo) token.getTokenInfo()).getValue())));
                        break;
                    case Token.OPERATION_PLUS:
                        nodeList.add(new Node(map.get("+"), null));
                        break;
                    case Token.OPERATION_MINUS:
                        nodeList.add(new Node(map.get("-"), null));
                        break;
                    case Token.OPERATION_MULTIPLY:
                        nodeList.add(new Node(map.get("*"), null));
                        break;
                    case Token.OPERATION_DIVIDE:
                        nodeList.add(new Node(map.get("/"), null));
                        break;
                    case Token.OPERATION_LEFT_BRACKET:
                        nodeList.add(new Node(map.get("("), null));
                        break;
                    case Token.OPERATION_RIGHT_BRACKET:
                        nodeList.add(new Node(map.get(")"), null));
                        break;
                }
            }

            node = grammar.parse(nodeList);
            if (node.getNodeInfo() instanceof DoubleNodeInfo)
                inputLabel.setText(String.valueOf(((DoubleNodeInfo) node.getNodeInfo()).getValue()));
            else
                inputLabel.setText(String.valueOf(((IntNodeInfo) node.getNodeInfo()).getValue()));
            stage = new Stage();
            pane = new Pane();
            Scene scene = new Scene(pane);
            drawTree(pane, node);
            stage.setScene(scene);
            content = "";
            stage.show();
        } catch (LexerException | ParserException e) {
            content = "";
            inputLabel.setText("Wrong Input");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
package Lexer;

public class DoubleTokenInfo extends TokenInfo {

    private double value;

    DoubleTokenInfo(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

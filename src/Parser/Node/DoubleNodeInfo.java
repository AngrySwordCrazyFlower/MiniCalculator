package Parser.Node;

public class DoubleNodeInfo extends NodeInfo {

    private double value;

    public DoubleNodeInfo(double value) {
        super(NodeInfo.DOUBLE_NODE);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}

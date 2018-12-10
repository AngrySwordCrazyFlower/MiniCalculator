package Parser.Node;

public class IntNodeInfo extends NodeInfo {

    private int value;

    public IntNodeInfo(int value) {
        super(NodeInfo.INT_NODE);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

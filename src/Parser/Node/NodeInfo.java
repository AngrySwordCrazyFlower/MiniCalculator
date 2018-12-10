package Parser.Node;

public abstract class NodeInfo {

    public static final int DOUBLE_NODE = 101;
    public static final int INT_NODE = 102;

    int type;

    NodeInfo(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

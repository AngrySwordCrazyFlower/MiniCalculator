package Parser.Node;

import Parser.AbstractElement;

import java.util.ArrayList;
import java.util.List;

public class Node {

    Node parent;
    List<Node> sons;
    public AbstractElement abstractElement;
    NodeInfo nodeInfo;

    public Node(AbstractElement abstractElement, NodeInfo nodeInfo) {
        this.abstractElement = abstractElement;
        sons = new ArrayList<>();
        this.nodeInfo = nodeInfo;
    }

    public void setSons(List<Node> nodes) {
        sons = nodes;
    }

    public List<Node> getSons() {
        return sons;
    }

    public AbstractElement getAbstractElement() {
        return abstractElement;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node))
            return false;
        Node another = (Node) obj;
        return parent.equals(another.parent) && sons.equals(another.sons) && another.abstractElement.equals(abstractElement);
    }

    @Override
    public String toString() {
        return abstractElement.toString();
    }
}

package MyParser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    Node parent;
    List<Node> sons;
    AbstractElement abstractElement;

    public Node(AbstractElement abstractElement) {
        this.abstractElement = abstractElement;
        sons = new ArrayList<>();
    }

    public void addSon(Node node) {
        sons.add(0, node);
        node.parent = this;
    }

    @Override
    public String toString() {
        return abstractElement.toString();
    }
}

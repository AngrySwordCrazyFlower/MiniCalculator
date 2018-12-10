package Parser;

import Parser.Node.Node;

import java.util.List;
import java.util.Stack;

public interface ReduceAction {

    void performAction(Stack<Node> nodeStack, Stack<Integer> stateStack);

}

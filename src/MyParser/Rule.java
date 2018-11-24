package MyParser;

import java.util.List;

public class Rule {

    NotTerminalElement leftNotTerminalElement;
    List<AbstractElement> rightAbstractElementList;

    Rule(NotTerminalElement notTerminalElement, List<AbstractElement> abstractElementList) {
        this.leftNotTerminalElement = notTerminalElement;
        this.rightAbstractElementList = abstractElementList;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rule))
            return false;
        if (obj == this)
            return true;
        Rule another = (Rule) obj;
        return this.leftNotTerminalElement.equals(another.leftNotTerminalElement) && this.rightAbstractElementList.equals(another.rightAbstractElementList);
    }
}

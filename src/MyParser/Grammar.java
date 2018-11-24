package MyParser;

import java.util.*;

public class Grammar {

    private Map<String, NotTerminalElement> notTerminalElementMap;

    private Map<String, TerminalElement> terminalElementMap;

    private List<Rule> rules;

    public Grammar(String rootText, String terminalText, String notTerminalText, String[] rulesText) throws ParserException {
        String root = getRoot(rootText);
        notTerminalElementMap = getNotTerminalElements(notTerminalText);
        terminalElementMap = getTerminalElements(terminalText);
        rules = new ArrayList<>();

        //Add a rule such as S' -> S#
        String startNotTerminal = root + "'";
        notTerminalElementMap.put(startNotTerminal, new NotTerminalElement(startNotTerminal));
        terminalElementMap.put("#", new TerminalElement("#"));
        rules.addAll(getRule(root + "' -> " + root));
        for (String ruleText : rulesText)
            rules.addAll(getRule(ruleText));
    }

    private String getRoot(String root) {
        return root.trim();
    }

    private Map<String, NotTerminalElement> getNotTerminalElements(String notTerminalText) {
        notTerminalText = notTerminalText.trim();
        String[] result = notTerminalText.split(" ");
        Map<String, NotTerminalElement> notTerminalElementMap = new HashMap<String, NotTerminalElement>();
        for (String element : result)
            notTerminalElementMap.put(element, new NotTerminalElement(element));
        return notTerminalElementMap;
    }

    private Map<String, TerminalElement> getTerminalElements(String terminalText) {
        terminalText = terminalText.trim();
        String[] result = terminalText.split(" ");
        Map<String, TerminalElement> terminalElementHashMap = new HashMap<String, TerminalElement>();
        for (String element : result)
            terminalElementHashMap.put(element, new TerminalElement(element));
        return terminalElementHashMap;
    }

    private Set<Rule> getRule(String ruleText) throws ParserException {
        //找到 -> 的位置， 左右分割， 如果左边的在非终符号里找不到， 报错
        int index = ruleText.indexOf("->");
        String left = ruleText.substring(0, index).trim();
        NotTerminalElement leftNotTerminalElement = notTerminalElementMap.get(left);
        if (null == leftNotTerminalElement)
            throw new ParserException(0, 0, MyParser.ParserException.GRAMMAR_EXCEPTION);

        //将右边的按|分割成多个生成式，  对于每个生成式， 按空格分割成Element，然后在集合里寻找， 并new Rule
        String right = ruleText.substring(index + 2);
        String[] splitRight = right.split("\\|");
        Set<Rule> rules = new HashSet<Rule>();
        AbstractElement rightAbstractElement;
        List<AbstractElement> abstractElementList;
        for (String rule : splitRight) {
            String[] elements = rule.trim().split(" ");
            abstractElementList = new ArrayList<AbstractElement>();
            for (String rightElement : elements) {
                rightAbstractElement = notTerminalElementMap.get(rightElement);
                if (null != rightAbstractElement) {
                    abstractElementList.add(rightAbstractElement);
                    continue;
                }
                rightAbstractElement = terminalElementMap.get(rightElement);
                if (null != rightAbstractElement) {
                    abstractElementList.add(rightAbstractElement);
                    continue;
                }
                throw new ParserException(0, 0, ParserException.WRONG_SYMBOL);
            }
            rules.add(new Rule(leftNotTerminalElement, abstractElementList));
        }
        return rules;
    }

    public List<Rule> getRules() {
        return rules;
    }
}

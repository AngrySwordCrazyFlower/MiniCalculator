import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CustomVariableTable {

    static {
        GLOBAL_VARIABLE_TABLE = new CustomVariableTable(Scope.GLOBAL_SCOPE, null);
    }

    public static final CustomVariableTable GLOBAL_VARIABLE_TABLE;

    private final CustomVariableTable parent;

    private final Scope scope;

    private final ConcurrentHashMap<String, Token> hashMap;

    static class Scope {

        public static final Scope LOCAL_SCOPE = new Scope();

        private static final Scope GLOBAL_SCOPE = new Scope();

        private Scope() {

        }
    }

    public CustomVariableTable(Scope scope, CustomVariableTable parent) {
        this.parent = parent;
        this.scope = scope;
        this.hashMap = new ConcurrentHashMap<>();
    }

    public Token putToken(String name, Token token) {
        return hashMap.put(name, token);
    }

    public boolean exists(String name) {
        CustomVariableTable current = this;
        while (null != current) {
            if (current.hashMap.containsKey(name))
                return true;
            current = current.parent;
        }
        return false;
    }

    public Token getTokenByName(String name) {
        CustomVariableTable current = this;
        Token result = null;
        while (null != current) {
            if (null != (result = current.hashMap.get(name)))
                break;
            current = current.parent;
        }
        return result;
    }

}

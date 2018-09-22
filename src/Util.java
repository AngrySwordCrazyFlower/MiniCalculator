import java.util.HashSet;
import java.util.Set;

public class Util {

    public final static Set<String> SINGLE_OPERATION_COLLECTION = new HashSet<>();

    public final static Set<String> BLANK_OPERATION_COLLECTION = new HashSet<>();

    static {
        SINGLE_OPERATION_COLLECTION.add("+");
        SINGLE_OPERATION_COLLECTION.add("-");
        SINGLE_OPERATION_COLLECTION.add("*");
        SINGLE_OPERATION_COLLECTION.add("/");
        SINGLE_OPERATION_COLLECTION.add("|");
        SINGLE_OPERATION_COLLECTION.add("&");

        BLANK_OPERATION_COLLECTION.add(" ");
        BLANK_OPERATION_COLLECTION.add("\n");
        BLANK_OPERATION_COLLECTION.add("\r");
        BLANK_OPERATION_COLLECTION.add("\0");
    }

}

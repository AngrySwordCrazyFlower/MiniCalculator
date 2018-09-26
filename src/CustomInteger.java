public class CustomInteger extends AbstractType {

    private int value;

    public CustomInteger(int value) {
        super(AbstractType.Type.INTEGER);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

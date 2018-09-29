public class CustomDouble extends AbstractType {

    private double value;

    public CustomDouble(double value) {
        super(AbstractType.Type.INTEGER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

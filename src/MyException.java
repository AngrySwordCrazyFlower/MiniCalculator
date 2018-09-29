public class MyException extends Exception {

    private int columnIndex;

    private int rowIndex;

    public MyException(int rowIndex, int columnIndex, String message) {
        super(message);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    @Override
    public String toString() {
        return "Exception (" + rowIndex + ", " + columnIndex + ") : " + getMessage();
    }

}

public class MyException extends Exception {

    private int columnIndex;

    public MyException(int columnIndex, String message) {
        super(message);
        this.columnIndex = columnIndex;
    }


}

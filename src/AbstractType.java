public abstract class AbstractType {

    private Type type;


    //自定义类型的类型
    static class Type {

        public static final Type INTEGER = new Type();

        public static final Type DOUBLE = new Type();

        private Type() {

        }
    }

    protected AbstractType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }



}

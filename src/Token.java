public class Token {

    static class TokenType {

        public static final TokenType INTEGER = new TokenType();

        public static final TokenType DOUBLE = new TokenType();

        public static final TokenType VARIABLE = new TokenType();

        public static final TokenType OPERATION = new TokenType();

        private TokenType() {

        }
    }

    private String name;

    private TokenType tokenType;

    public static Token creator(String s, TokenType tokenType) {

        //have something to do with s : like if s=".5" change it to "0.5"

        if (tokenType == TokenType.INTEGER) {
            return new Token(s, tokenType);
        } else if (tokenType == TokenType.DOUBLE) {
            return new Token(s, tokenType);
        } else if (tokenType == TokenType.OPERATION) {
            return new Token(s, tokenType);
        } else if (tokenType == TokenType.VARIABLE) {
            return new Token(s, tokenType);
        }
        return null;
    }

    private Token(String s, TokenType tokenType) {
        this.name = s;
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getName() {
        return name;
    }

}

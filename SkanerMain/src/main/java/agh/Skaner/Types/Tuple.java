package agh.Skaner.Types;

public class Tuple {
    public Token getToken() {
        return token;
    }

    public String getValue() {
        return value;
    }

    private final Token token;
        private final String value;

        public Tuple(Token token, String value)
        {
            this.token = token;
            this.value = value;
        }

    @Override
    public String toString() {
        return "(" + token.name() + ";" + value + ")";
    }
}

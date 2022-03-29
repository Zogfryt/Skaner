package agh.Skaner.Types;

public class Tuple {
        private Token token;
        private String value;

        public Tuple(Token token, String value)
        {
            this.token = token;
            this.value = value;
        }

    @Override
    public String toString() {
        return "(" + token.name() + "," + value + ")";
    }
}

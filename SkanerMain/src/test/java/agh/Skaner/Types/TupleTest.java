package agh.Skaner.Types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TupleTest {

    private Tuple testTuple;

    @Test
    @DisplayName("Simple to string test 1")
    void toStringTest1()
    {
        testTuple = new Tuple(Token.INT_NUMBER,"234");
        Assertions.assertEquals("(INT_NUMBER;234)",testTuple.toString());
    }

    @Test
    @DisplayName("Simple to string test 2")
    void toStringTest2()
    {
        testTuple = new Tuple(Token.DIVISION,"");
        Assertions.assertEquals("(DIVISION;)",testTuple.toString());
    }
}

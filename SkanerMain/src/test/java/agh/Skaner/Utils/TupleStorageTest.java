package agh.Skaner.Utils;

import agh.Skaner.Types.Token;
import agh.Skaner.Types.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TupleStorageTest {

    private TupleStorage storage;

    @BeforeEach
    void setUp()
    {
        storage = new TupleStorage();
    }

    @Test
    @DisplayName("Simple to string test")
    void toStringTest()
    {
        storage.add(new Tuple(Token.DIVISION,""));
        storage.add(new Tuple(Token.INT_NUMBER,"234"));
        storage.add(new Tuple(Token.FLOAT_NUMBER,"13.45"));

        Assertions.assertEquals("(DIVISION;),(INT_NUMBER;234),(FLOAT_NUMBER;13.45)",storage.toString());
    }
    @Test
    @DisplayName("Single tuple write test")
    void SingleTupleTest()
    {
        storage.add(new Tuple(Token.INT,""));
        Assertions.assertEquals("(INT;)",storage.toString());

    }
    @Test
    @DisplayName("fast add test")
    void addTest()
    {
        storage.add(new Tuple(Token.FLOAT_NUMBER,"12.45"));
        storage.add(new Tuple(Token.FLOAT_NUMBER,"12.45"));
        storage.add(new Tuple(Token.INT_NUMBER,"33"));

        Assertions.assertEquals(3,storage.count());
    }

    @Test
    @DisplayName("count test")
    void countTest()
    {
        List<Tuple> temp = new ArrayList<>();
        temp.add(new Tuple(Token.INT_NUMBER,"33"));
        temp.add(new Tuple(Token.FLOAT_NUMBER,"12.45"));

        storage = new TupleStorage(temp);
        Assertions.assertEquals(2,storage.count());
    }
}

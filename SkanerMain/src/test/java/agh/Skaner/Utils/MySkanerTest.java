package agh.Skaner.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MySkanerTest {

    private MySkaner scanner;

    private void setUpScanner(String path)
    {
            scanner = new MySkaner(path);
    }


    @Test
    @DisplayName("Single number scan test")
    void singleNumberScanTest() throws IOException {
        setUpScanner("src/test/resources/SingleNumberInt");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;234),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Muliple numbers scan test")
    void multipleIntNumbersScan() throws IOException {
        setUpScanner("src/test/resources/MultipleNumbersInt");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;123),(WHITE_SPACE; ),(INT_NUMBER;12),(WHITE_SPACE; ),(INT_NUMBER;23423),(WHITE_SPACE; ),(INT_NUMBER;899),(WHITE_SPACE; ),(INT_NUMBER;456),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Flooat number reading Test")
    void floatReadingTest() throws IOException {
        setUpScanner("src/test/resources/SingleFloatNumber");
        scanner.startScanning();
        Assertions.assertEquals("(FLOAT_NUMBER;123.45),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Multiple float numbers test")
    void multipleFloatTest() throws IOException {
        setUpScanner("src/test/resources/MultipleFloatNumber");
        scanner.startScanning();
        Assertions.assertEquals("(FLOAT_NUMBER;123.45),(WHITE_SPACE; ),(FLOAT_NUMBER;46.77),(WHITE_SPACE; ),(FLOAT_NUMBER;123.),(END_OF_FILE;)",scanner.returnListOfTuples());

    }

    @Test
    @DisplayName("Mixed numbers read")
    void MixedReadTest() throws IOException {
        setUpScanner("src/test/resources/MixedNumbers");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;45),(WHITE_SPACE; ),(FLOAT_NUMBER;123.4),(WHITE_SPACE; ),(INT_NUMBER;1),(WHITE_SPACE; ),(FLOAT_NUMBER;90.2),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Arithmetic symbols read")
    void arithmeticSymbolsReadTest() throws IOException {
        setUpScanner("src/test/resources/AritheticSymbols");
        scanner.startScanning();
        Assertions.assertEquals("(LEFT_PARENTHESIS;),(PLUS;),(MINUS;),(MULTIPLICATION;),(DIVISION;),(RIGHT_PARENTHESIS;),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("ID reading test")
    void IDreadingtest() throws IOException {
        setUpScanner("src/test/resources/IdSingle");
        scanner.startScanning();
        Assertions.assertEquals("(ID;Jacek_W3n4),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Tabulator test")
    void tabulatorTest() throws IOException {
        setUpScanner("src/test/resources/rawTabulatorFile.txt");
        scanner.startScanning();
        String build = "(WHITE_SPACE;\t),(END_OF_FILE;)";
        Assertions.assertEquals(build,scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Whole Equasion Test")
     void readingEquasionTest() throws IOException {
        setUpScanner("src/test/resources/ReadingWholeEquasionCorrect.txt");
        scanner.startScanning();
        Assertions.assertEquals("(ID;Moja_zmienna),(WHITE_SPACE; ),(ASSIGNMENT;),(WHITE_SPACE; ),(FLOAT_NUMBER;45.6),(WHITE_SPACE; ),(PLUS;),(WHITE_SPACE; ),(INT_NUMBER;7),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Error handling test")
    void errorHandlingTest() throws IOException {
        setUpScanner("src/test/resources/ErrorHandlingTest.txt");
        scanner.startScanning();
        Assertions.assertEquals("(ERROR;Ada@val),(WHITE_SPACE;\r),(WHITE_SPACE;\n),(FLOAT_NUMBER;3.4),(WHITE_SPACE; ),(ERROR;2.3.4),(WHITE_SPACE; ),(ERROR;24.d),(WHITE_SPACE; ),(ID;val2),(END_OF_FILE;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Whole Equasion test")
    void WholeEquasionTest() throws IOException {
        setUpScanner("src/test/resources/WholeEquasionWithError.txt");
        scanner.startScanning();
        Assertions.assertEquals("(INT;),(WHITE_SPACE; ),(ID;intvalue),(WHITE_SPACE; ),(ASSIGNMENT;),(WHITE_SPACE; ),(FLOAT_NUMBER;5.6),(PLUS;),(ERROR;7d),(END_OF_FILE;)",scanner.returnListOfTuples());

    }


}

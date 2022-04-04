package agh.Skaner.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MySkanerTest {

    private MySkaner scanner;

    private void setUpScanner(String path)
    {
        PushbackReader returnScanner = null;
        try{
            FileInputStream file = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
            returnScanner = new PushbackReader(isr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner = new MySkaner(returnScanner);
    }


    @Test
    @DisplayName("Single number scan test")
    void singleNumberScanTest() throws IOException {
        setUpScanner("src/test/resources/SingleNumberInt");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;234)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Muliple numbers scan test")
    void multipleIntNumbersScan() throws IOException {
        setUpScanner("src/test/resources/MultipleNumbersInt");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;123),(INT_NUMBER;12),(INT_NUMBER;23423),(INT_NUMBER;899),(INT_NUMBER;456)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Flooat number reading Test")
    void floatReadingTest() throws IOException {
        setUpScanner("src/test/resources/SingleFloatNumber");
        scanner.startScanning();
        Assertions.assertEquals("(FLOAT_NUMBER;123.45)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Multiple float numbers test")
    void multipleFloatTest() throws IOException {
        setUpScanner("src/test/resources/MultipleFloatNumber");
        scanner.startScanning();
        Assertions.assertEquals("(FLOAT_NUMBER;123.45),(FLOAT_NUMBER;46.77),(FLOAT_NUMBER;123.)",scanner.returnListOfTuples());

    }

    @Test
    @DisplayName("Mixed numbers read")
    void MixedReadTest() throws IOException {
        setUpScanner("src/test/resources/MixedNumbers");
        scanner.startScanning();
        Assertions.assertEquals("(INT_NUMBER;45),(FLOAT_NUMBER;123.4),(INT_NUMBER;1),(FLOAT_NUMBER;90.2)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("Arithmetic symbols read")
    void arithmeticSymbolsReadTest() throws IOException {
        setUpScanner("src/test/resources/AritheticSymbols");
        scanner.startScanning();
        Assertions.assertEquals("(LEFT_PARENTHESIS;),(PLUS;),(MINUS;),(MULTIPLICATION;),(DIVISION;),(RIGHT_PARENTHESIS;)",scanner.returnListOfTuples());
    }

    @Test
    @DisplayName("ID reading test")
    void IDreadingtest() throws IOException {
        setUpScanner("src/test/resources/IdSingle");
        scanner.startScanning();
        Assertions.assertEquals("(ID;Jacek_W3n4)",scanner.returnListOfTuples());
    }
}

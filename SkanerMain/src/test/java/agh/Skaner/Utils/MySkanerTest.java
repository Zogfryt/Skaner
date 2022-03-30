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
}

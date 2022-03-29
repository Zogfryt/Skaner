package agh.Skaner.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class MySkaner {

    private BufferedReader theScanner;

    public MySkaner(BufferedReader scan)
    {
        theScanner = scan;
    }

    public void startScanning() throws IOException {
        int c;
        while((c = theScanner.read()) != -1)
        {

        }
    }
}

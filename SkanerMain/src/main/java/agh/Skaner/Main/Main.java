package agh.Skaner.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            LOGGER.log(Level.INFO,"Musisz podać jedną ścieżkę do pliku");
            return;
        }

        BufferedReader initialScanner = getScanner(args[1]);


    }

    private static BufferedReader getScanner(String path)
    {
        BufferedReader returnScanner = null;
        try{
            FileInputStream file = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
            returnScanner = new BufferedReader(isr);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO,"plik nie istnieje");
            System.exit(100);
        }
        return returnScanner;
    }
}

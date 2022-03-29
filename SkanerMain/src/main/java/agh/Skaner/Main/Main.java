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

        PushbackReader initialScanner = getScanner(args[1]);


    }

    private static PushbackReader getScanner(String path)
    {
        PushbackReader returnScanner = null;
        try{
            FileInputStream file = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
            returnScanner = new PushbackReader(isr);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO,"plik nie istnieje");
            System.exit(100);
        }
        return returnScanner;
    }
}

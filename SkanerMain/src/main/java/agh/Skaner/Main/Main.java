package agh.Skaner.Main;

import agh.Skaner.Types.Token;
import agh.Skaner.Types.Tuple;
import agh.Skaner.Utils.ConvertingToHTML;
import agh.Skaner.Utils.TupleStorage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args) throws IOException {
        /*   SORRY TESTUJE SE FUNKCJE
        if (args.length != 2)
        {
            LOGGER.log(Level.INFO,"Musisz podać jedną ścieżkę do pliku");
            return;
        }

        PushbackReader initialScanner = getScanner(args[1]);
        */
        TupleStorage tupleStorage = new TupleStorage();

        Tuple tuple1 = new Tuple(Token.INT,"dupa");
        Tuple tuple2 = new Tuple(Token.ASSIGNMENT,"=");
        Tuple tuple3 = new Tuple(Token.LEFT_PARENTHESIS,"(");
        Tuple tuple4 = new Tuple(Token.INT_NUMBER,"30");
        Tuple tuple5 = new Tuple(Token.END_OF_FILE,"EOF");
        Tuple tuple6 = new Tuple(Token.ERROR,"ERROR");

        tupleStorage.add(tuple1);
        tupleStorage.add(tuple2);
        tupleStorage.add(tuple3);
        tupleStorage.add(tuple4);
        tupleStorage.add(tuple5);
        tupleStorage.add(tuple6);

        ConvertingToHTML.Convert("file.html",tupleStorage.getListOfTuples());

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

package agh.Skaner.Main;

import agh.Skaner.Utils.MySkaner;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args) {
        if (args.length != 1)
        {
            LOGGER.log(Level.INFO,"Musisz podać jedną ścieżkę do pliku");
            return;
        }

        try{
            MySkaner scanner = new MySkaner(args[0]);
            scanner.startScanning();
            scanner.printToHtml();
        }
        catch (IOException e)
        {
            LOGGER.log(Level.INFO,"Unknown Error" + e);
        }


//        TupleStorage tupleStorage = new TupleStorage();

//        Tuple tuple1 = new Tuple(Token.INT,"dupa");
//        Tuple tuple2 = new Tuple(Token.ASSIGNMENT,"=");
//        Tuple tuple3 = new Tuple(Token.LEFT_PARENTHESIS,"(");
//        Tuple tuple4 = new Tuple(Token.INT_NUMBER,"30");
//        Tuple tuple5 = new Tuple(Token.END_OF_FILE,"EOF");
//        Tuple tuple6 = new Tuple(Token.ERROR,"ERROR");
//
//        tupleStorage.add(tuple1);
//        tupleStorage.add(tuple2);
//        tupleStorage.add(tuple3);
//        tupleStorage.add(tuple4);
//        tupleStorage.add(tuple5);
//        tupleStorage.add(tuple6);
//
//        ConvertingToHTML.Convert("file.html",tupleStorage.getListOfTuples());

    }
}

package agh.Skaner.Utils;

import agh.Skaner.Types.Token;
import agh.Skaner.Types.Tuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConvertingToHTML {
    public static void Convert(String filepath, List<Tuple> tupleList) throws IOException {

        File f = new File(filepath); //.html file !
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        for(Tuple tuple : tupleList){
            switch (tuple.getToken()) {
                //zielony kolor
                case INT,FLOAT -> {
                    bw.write("<span style=\"color: green\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }

                //czerwony kolor
                case LEFT_PARENTHESIS,RIGHT_PARENTHESIS -> {
                    bw.write("<span style=\"color: red\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }

                //fioletowy kolor
                case PLUS,MINUS,MULTIPLICATION,DIVISION,ASSIGNMENT -> {
                    bw.write("<span style=\"color: purple\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }


                //niebieski kolor
                case FLOAT_NUMBER, INT_NUMBER -> {
                    bw.write("<span style=\"color: blue\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }

                //kolor tła
                case END_OF_FILE -> {
                    bw.write("<span style=\"color: transparent\">" + tuple.getValue() + "</span>");
                    bw.newLine();   //trzeba nie trzeba?
                }
            }

        }
        bw.close();
    }
}

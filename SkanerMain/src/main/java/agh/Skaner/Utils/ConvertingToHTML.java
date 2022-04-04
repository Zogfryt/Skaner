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
                case INT -> {
                    bw.write("<span style=\"color: green\">int</span>");
                    bw.newLine();
                }
                case FLOAT -> {
                    bw.write("<span style=\"color: green\">float</span>");
                    bw.newLine();
                }

                //czerwony kolor
                case LEFT_PARENTHESIS -> {
                    bw.write("<span style=\"color: red\">left_parenthesis</span>");
                    bw.newLine();
                }
                case RIGHT_PARENTHESIS -> {
                    bw.write("<span style=\"color: red\">right_parenthesis</span>");
                    bw.newLine();
                }

                //pomaranczowy kolor
                case PLUS -> {
                    bw.write("<span style=\"color: orange\">plus</span>");
                    bw.newLine();
                }
                case MINUS -> {
                    bw.write("<span style=\"color: orange\">minus</span>");
                    bw.newLine();
                }
                case MULTIPLICATION -> {
                    bw.write("<span style=\"color: orange\">multiplication</span>");
                    bw.newLine();
                }
                case DIVISION -> {
                    bw.write("<span style=\"color: orange\">division</span>");
                    bw.newLine();
                }
                case ASSIGNMENT -> {
                    bw.write("<span style=\"color: orange\">assigment</span>");
                    bw.newLine();
                }

                //niebieski kolor
                case FLOAT_NUMBER -> {
                    bw.write("<span style=\"color: blue\">float_number</span>");
                    bw.newLine();
                }
                case INT_NUMBER -> {
                    bw.write("<span style=\"color: blue\">int_number</span>");
                    bw.newLine();
                }

                //zolty kolor
                case END_OF_FILE -> {
                    bw.write("<span style=\"color: yellow\">end_of_file</span>");
                    bw.newLine();   //trzeba nie trzeba?
                }
            }

        }
        bw.close();
    }
}

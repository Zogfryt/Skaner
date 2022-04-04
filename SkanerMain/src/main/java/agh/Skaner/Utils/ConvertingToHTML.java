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
                case INT-> {
                    bw.write("<span style=\"color: green\">int</span>");
                    bw.newLine();
                }
                case FLOAT-> {
                    bw.write("<span style=\"color: green\">float</span>");
                    bw.newLine();
                }


                //pomaranczowy kolor
                case LEFT_PARENTHESIS-> {
                    bw.write("<span style=\"color: orange\">(</span>");
                    bw.newLine();
                }
                case RIGHT_PARENTHESIS-> {
                    bw.write("<span style=\"color: orange\">)</span>");
                    bw.newLine();
                }

                //fioletowy kolor
                case PLUS -> {
                    bw.write("<span style=\"color: purple\">+</span>");
                    bw.newLine();
                }
                case MINUS -> {
                    bw.write("<span style=\"color: purple\">-</span>");
                    bw.newLine();
                }
                case MULTIPLICATION -> {
                    bw.write("<span style=\"color: purple\">*</span>");
                    bw.newLine();
                }
                case DIVISION -> {
                    bw.write("<span style=\"color: purple\">/</span>");
                    bw.newLine();
                }
                case ASSIGNMENT -> {
                    bw.write("<span style=\"color: purple\">=</span>");
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

                //czerwony kolor
                case ERROR -> {
                    bw.write("<span style=\"color: red; text_decoration: underline\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }
                //rozowy kolor
                case ID -> {
                    bw.write("<span style=\"color: pink\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }
                //cyan?
                case WHITE_SPACE -> {
                    bw.write("<span style=\"color: cyan\">" + tuple.getValue() + "</span>");
                    bw.newLine();
                }

            }

        }
        bw.close();
    }
}

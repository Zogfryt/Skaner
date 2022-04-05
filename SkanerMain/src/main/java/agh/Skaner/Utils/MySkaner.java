package agh.Skaner.Utils;

import agh.Skaner.Main.Main;
import agh.Skaner.Types.Token;
import agh.Skaner.Types.Tuple;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySkaner {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
    private PushbackReader theScanner;
    private String outFilePath;
    private final TupleStorage listOfTokens;
    private final List<Character> arithmeticSymbols = new ArrayList<>(Arrays.asList('-','+','=','*','/','(',')'));
    private int listOfRows;

    public MySkaner(String path)
    {
        getScanner(path);
        getOutPath(path);
        listOfTokens = new TupleStorage();
        listOfRows = 0;
    }

    private void getScanner(String path)
    {
        try{
            FileInputStream file = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
            theScanner = new PushbackReader(isr);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO,"plik nie istnieje");
            theScanner = null;
            System.exit(100);
        }
    }

    private void getOutPath(String path)
    {
        int positionOfDot = path.lastIndexOf('.');
        if (positionOfDot < 0)
        {
            outFilePath = path + ".html";
            return;
        }
        String pathWithoutExtension = path.substring(0,positionOfDot);
        outFilePath = pathWithoutExtension + ".html";
    }

    public void startScanning() throws IOException {
        int c;
        while((c = theScanner.read()) != -1)
        {
            char ch = (char) c;
            if (Character.isDigit(ch))
            {
                GetDigit(ch);
            }
            else if (arithmeticSymbols.contains(ch))
            {
                getSpecialSymbol(ch);
            }
            else if (Character.isWhitespace(ch))
            {
                getWhiteSymbol(ch);
            }
            else if (Character.isLetter(ch) || ch == '_')
            {
                getIdName(ch);
            }
        }
        listOfTokens.add(new Tuple(Token.END_OF_FILE,""));
    }

    private void GetDigit(char firstChar) throws IOException {
        StringBuilder build = new StringBuilder();
        boolean dotApppeared = false;
        build.append(firstChar);
        int c;
        while((c = theScanner.read()) != -1)
        {

            char ch = (char)c;
            if (ch == '.' && !dotApppeared)
            {
                dotApppeared = true;
            }
            else if (ch == '.')
            {
                LOGGER.log(Level.INFO,"Błąd: Podwójna kropka | line: " + listOfRows);
                build.append(ch);
                String errorValue = SkipToEndOfError(build.toString());
                listOfTokens.add(new Tuple(Token.ERROR,errorValue));
                return;
            }
            else if(Character.isWhitespace(ch) && dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.FLOAT_NUMBER,build.toString()));
                theScanner.unread(c);
                return;
            }
            else if (Character.isWhitespace(ch) && !dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.INT_NUMBER,build.toString()));
                theScanner.unread(c);
                return;
            }
            else if (arithmeticSymbols.contains(ch) && !dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.INT_NUMBER,build.toString()));
                theScanner.unread(c);
                return;
            }
            else if (arithmeticSymbols.contains(ch) && dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.FLOAT_NUMBER, build.toString()));
                theScanner.unread(c);
                return;
            }
            else if (!Character.isDigit(ch))
            {
                LOGGER.log(Level.INFO,"Nieautoryzowany znak, zmienna deklarujemy nie używając na początku liczb | line: " + listOfRows);
                build.append(ch);
                String errorValue = SkipToEndOfError(build.toString());
                listOfTokens.add(new Tuple(Token.ERROR,errorValue));
                return;
            }

            build.append(ch);
        }

        if (dotApppeared)
        {
            listOfTokens.add(new Tuple(Token.FLOAT_NUMBER,build.toString()));
        }
        else
        {
            listOfTokens.add(new Tuple(Token.INT_NUMBER,build.toString()));
        }

    }

    private void getSpecialSymbol(char symbol) {
        switch (symbol) {
            case '-' -> listOfTokens.add(new Tuple(Token.MINUS, ""));
            case '+' -> listOfTokens.add(new Tuple(Token.PLUS,""));
            case '/' -> listOfTokens.add(new Tuple(Token.DIVISION,""));
            case '*' -> listOfTokens.add(new Tuple(Token.MULTIPLICATION,""));
            case '(' -> listOfTokens.add(new Tuple(Token.LEFT_PARENTHESIS,""));
            case ')' -> listOfTokens.add(new Tuple(Token.RIGHT_PARENTHESIS,""));
            case '=' -> listOfTokens.add(new Tuple(Token.ASSIGNMENT,""));
        }
    }

    private void getWhiteSymbol(char symbol)
    {
        if (symbol == '\n')
        {
            listOfRows++;
        }
        listOfTokens.add(new Tuple(Token.WHITE_SPACE,Character.toString(symbol)));
    }

    private void getIdName(char first) throws IOException {
        StringBuilder build = new StringBuilder();
        build.append(first);
        int c;
        while ((c = theScanner.read()) != -1)
        {
            char ch = (char)c;
            if (Character.isSpaceChar(ch) || arithmeticSymbols.contains(ch))
            {
                lookAfterFixedWords(build.toString());
                theScanner.unread(c);
                return;
            }
            if (!Character.isLetterOrDigit(ch) && ch != '_')
            {
                LOGGER.log(Level.INFO,"Zła nazwa zmiennej | line: " + listOfRows);
                build.append(ch);
                String errorValue = SkipToEndOfError(build.toString());
                listOfTokens.add(new Tuple(Token.ERROR,errorValue));
                return;
            }
            build.append(ch);
        }

        listOfTokens.add(new Tuple(Token.ID,build.toString()));
    }

    private void lookAfterFixedWords(String word)
    {
        if (word.equals("int"))
        {
            listOfTokens.add(new Tuple(Token.INT,""));
            return;
        }

        if (word.equals("float"))
        {
            listOfTokens.add(new Tuple(Token.FLOAT,""));
            return;
        }

        listOfTokens.add(new Tuple(Token.ID,word));
    }

    private String SkipToEndOfError(String text) throws IOException {
        int c;
        StringBuilder build = new StringBuilder();
        build.append(text);
        while ((c = theScanner.read()) != -1)
        {
            char ch = (char)c;
            if (Character.isWhitespace(ch) || arithmeticSymbols.contains(ch))
            {
                theScanner.unread(c);
                return build.toString();
            }
            build.append(ch);
        }
        return text;
    }

    public void printToHtml() throws IOException {
        ConvertingToHTML.Convert(outFilePath,listOfTokens.getListOfTuples());
    }

    protected String returnListOfTuples()
    {
        return listOfTokens.toString();
    }


}

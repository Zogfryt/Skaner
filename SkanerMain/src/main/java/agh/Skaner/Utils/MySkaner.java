package agh.Skaner.Utils;

import agh.Skaner.Main.Main;
import agh.Skaner.Types.Token;
import agh.Skaner.Types.Tuple;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySkaner {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
    private final PushbackReader theScanner;
    private final TupleStorage listOfTokens;
    private final List<Character> arithmeticSymbols = new ArrayList<>(Arrays.asList('-','+','=','*','/','(',')'));

    public MySkaner(PushbackReader scan)
    {
        theScanner = scan;
        listOfTokens = new TupleStorage();
    }

    public void startScanning() throws IOException {
        int c;
        while((c = theScanner.read()) != -1)
        {
            char ch = (char) c;
            if (Character.isSpaceChar(ch))
            {
                continue;
            }
            else if (Character.isDigit(ch))
            {
                GetDigit(ch);
            }
            else if (arithmeticSymbols.contains(ch))
            {
                getSpecialSymbol(ch);
            }
        }
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
                LOGGER.log(Level.INFO,"Błąd: Podwójna kropka");
                //TODO: Obsługa błędu
                return;
            }
            else if(Character.isSpaceChar(ch) && dotApppeared)
            {
                theScanner.unread(c);
                listOfTokens.add(new Tuple(Token.FLOAT_NUMBER,build.toString()));
                return;
            }
            else if (Character.isSpaceChar(ch) && !dotApppeared)
            {
                theScanner.unread(c);
                listOfTokens.add(new Tuple(Token.INT_NUMBER,build.toString()));
                return;
            }
            else if (arithmeticSymbols.contains(ch) && !dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.INT_NUMBER,build.toString()));
                getSpecialSymbol(ch);
                return;
            }
            else if (arithmeticSymbols.contains(ch) && dotApppeared)
            {
                listOfTokens.add(new Tuple(Token.FLOAT_NUMBER, build.toString()));
                getSpecialSymbol(ch);
                return;
            }
            else if (!Character.isDigit(ch))
            {
                LOGGER.log(Level.INFO,"Nieautoryzowany znak, zmienna deklarujemy nie używając na początku liczb");
                //TODO: Obługa błędu
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

    protected String returnListOfTuples()
    {
        return listOfTokens.toString();
    }


}

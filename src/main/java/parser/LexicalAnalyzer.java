package parser;

import java.text.ParseException;
import java.util.ArrayList;

class LexicalAnalyzer {
    private String is;
    private int curChar;
    private int curPos;
    Token curToken;
    ArrayList<String> numbers = new ArrayList<>();

    LexicalAnalyzer(String is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    private boolean isBlank(int c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.charAt(curPos - 1);
        } catch (IndexOutOfBoundsException e) {
            curChar = -1;
        }
    }

    void nextToken() throws ParseException {
        while (isBlank(curChar)) {
            nextChar();
        }
        switch (curChar) {
            case '(':
                nextChar();
                curToken = Token.LPAREN;
                break;
            case ')':
                nextChar();
                curToken = Token.RPAREN;
                break;
            case '\\':
                nextChar();
                curToken = Token.LAMBDA;
                break;
            case '.':
                nextChar();
                curToken = Token.DOT;
            case -1:
                curToken = Token.END;
                break;
            default:
                if (curChar <= 'z' && curChar >= 'a') {
                    numbers.add(findNumber());
                    curToken = Token.VAR;
                } else {
                    throw new ParseException("Illegal character " + (char) curChar, curPos);
                }
        }
    }

    private String findNumber() throws ParseException {
        String s = "";
        do {
            s += (char) curChar;
            nextChar();
        } while (curChar <= '9' && curChar >= '0' || curChar <= 'z' && curChar >= 'a' || curChar == '\'');
        return s;
    }

    Token curToken() {
        return curToken;
    }

    enum Token {
        LPAREN, RPAREN, END, LAMBDA, VAR, DOT
    }
}

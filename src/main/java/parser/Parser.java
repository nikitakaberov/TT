package parser;

import common.Structure;
import lambdas.LambdaStructure;

import java.text.ParseException;

public class Parser {
    private LexicalAnalyzer lex;

    private Structure expression() throws ParseException {
        switch (lex.curToken) {
            case LAMBDA:
                lex.nextToken();
                lex.nextToken();
                String t = lex.numbers.remove(0);
                lex.nextToken();
                Structure s = expression();
                return LambdaStructure.abstraction(t, s);
            case LPAREN:
            case VAR:
                Structure app = application();
                if (lex.curToken == LexicalAnalyzer.Token.LAMBDA) {
                    lex.nextToken();
                    lex.nextToken();
                    String t1 = lex.numbers.remove(0);
                    lex.nextToken();
                    Structure s1 = expression();
                    return LambdaStructure.application(app, LambdaStructure.abstraction(t1, s1));
                } else {
                    return app;
                }
            default:
                throw new AssertionError();
        }
    }

    private Structure application() throws ParseException {
        switch (lex.curToken) {
            case LPAREN:
            case VAR:
                Structure a = atomic();
                if (lex.curToken == LexicalAnalyzer.Token.LPAREN
                        || lex.curToken == LexicalAnalyzer.Token.VAR) {
                    Structure app = application();
                    return LambdaStructure.application(a, app);
                } else {
                    return a;
                }
            default:
                throw new AssertionError();
        }
    }

    private Structure atomic() throws ParseException {
        switch (lex.curToken) {
            case LPAREN:
                lex.nextToken();
                Structure e = expression();
                lex.nextToken();
                return e;
            case VAR:
                lex.nextToken();
                return LambdaStructure.variable(lex.numbers.remove(0));
            default:
                throw new AssertionError();
        }
    }

    public Structure parse(String is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        Structure s = expression();
        if (lex.curToken() != LexicalAnalyzer.Token.END) {
            throw new AssertionError();
        }
        return s;
    }
}


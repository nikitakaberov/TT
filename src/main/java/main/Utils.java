package main;

import common.Global;
import lambdas.Lambda;
import types.Manager;

import java.text.ParseException;

import static lambdas.LambdaStructure.parse;
import static lambdas.LambdaStructure.toLambdaStructure;

public class Utils {

    private final static Manager MANAGER = new Manager();

    private final static Global global = new Global();

    static Lambda toLambda(String str, boolean flag, boolean isFirst) throws ParseException {
        return isFirst
                ? parse(str).solve(Utils.global, flag)
                : toLambdaStructure(str).solve(Utils.global, flag);
    }

    public static Manager getManager() {
        return MANAGER;
    }
}

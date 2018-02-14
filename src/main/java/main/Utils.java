package main;

import common.Global;
import lambdas.Lambda;
import types.Manager;

import static lambdas.LambdaStructure.toLambdaStructure;

public class Utils {

    private final static Manager MANAGER = new Manager();

    private final static Global global = new Global();

    static Lambda toLambda(String str, boolean flag) {
        return toLambdaStructure(str).solve(Utils.global, flag);
    }

    public static Manager getManager() {
        return MANAGER;
    }
}

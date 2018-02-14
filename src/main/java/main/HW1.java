package main;

import java.io.BufferedWriter;

import static lambdas.Lambda.reduceFully;
import static main.Utils.toLambda;

class HW1 {
    static void runReduce(String str, BufferedWriter writer) {
        try {
            writer.append(reduceFully(toLambda(str, true)).toString());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

package main;

import lambdas.Lambda;
import lambdas.Var;
import types.Type;

import java.io.BufferedWriter;
import java.util.Set;

import static main.Utils.toLambda;

class HW3 {
    static void runType(String str, BufferedWriter writer) {
        try {
            Lambda lambda = toLambda(str, false);
            Type type = lambda.type();
            if (type != null) {
                type.concreteType();
                writer.append("Type: ")
                        .append(String.valueOf(type))
                        .append(System.lineSeparator());
                Set<Var> variables = lambda.getVariables();
                writer.append("Context:")
                        .append(variables.isEmpty() ? " empty" : "")
                        .append(System.lineSeparator());
                for (Var variable : variables) {
                    variable.type().concreteType();
                    writer.append("    ")
                            .append(String.valueOf(variable))
                            .append(" : ")
                            .append(String.valueOf(variable.type()))
                            .append(System.lineSeparator());
                }
            } else {
                writer.append("No type deduced");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

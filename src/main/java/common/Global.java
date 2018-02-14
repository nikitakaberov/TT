package common;

import lambdas.Var;

import java.util.HashMap;

import static common.Generators.varGenerator;

public class Global implements Scope {
    private final HashMap<String, Var> variables = new HashMap<>();

    public Global() {
    }

    @Override
    public Var find(String name, boolean flag) {
        Var exist = variables.get(name);
        if (exist != null) {
            return exist;
        } else {
            Var variable = new Var(flag ? varGenerator.nextName() : name);
            variables.put(name, variable);
            return variable;
        }
    }
}

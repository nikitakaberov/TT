package lambdas;

import types.Type;

import static main.Utils.getManager;

public class Var {

    private final String variable;

    public Var(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Var && variable.equals(((Var) other).variable);
    }

    public Type type() {
        return getManager().typeOf(this).monoType();
    }

}

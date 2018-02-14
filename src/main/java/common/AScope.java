package common;

import lambdas.Var;

public class AScope implements Scope {
    private final String name;
    private final Var var;
    private final Scope scope;

    public AScope(String name, Var var, Scope scope) {
        this.name = name;
        this.var = var;
        this.scope = scope;
    }

    @Override
    public Var find(String name, boolean flag) {
        return this.name.equals(name) ? var : this.scope.find(name, flag);
    }
}

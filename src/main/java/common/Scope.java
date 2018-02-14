package common;

import lambdas.Var;

public interface Scope {
    Var find(String name, boolean flag);
}

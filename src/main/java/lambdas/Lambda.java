package lambdas;

import types.Type;
import types.Manager;

import java.util.Collections;
import java.util.Set;

import static main.Utils.getManager;

public abstract class Lambda implements Container {

    Set<Var> variables = Collections.emptySet();

    public Container sub(Var varSubst, Container subst) {
        return this;
    }

    public abstract Set<Var> getVariables();

    public Lambda reduceLambda() {
        return null;
    }

    @Override
    public Lambda getLambda() {
        return this;
    }

    protected abstract boolean equals(Lambda other, VStack yourStack, VStack theirStack);

    protected abstract int hashCode(VStack stack);

    public static Lambda reduceFully(Lambda lambda) {
        Lambda reduced = lambda.reduceLambda();
        while (reduced != null) {
            lambda = reduced;
            reduced = lambda.reduceLambda();
        }
        return lambda;
    }

    protected abstract Type toType(Manager manager);

    public Type type() {
        return toType(getManager());
    }

    @Override
    public Container comp() {
        return new Computation(getLambda());
    }

}

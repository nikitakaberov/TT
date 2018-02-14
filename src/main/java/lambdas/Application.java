package lambdas;

import types.Function;
import types.Type;
import types.Manager;

import java.util.HashSet;
import java.util.Set;

public class Application extends Lambda {

    private final Container function;
    private final Container argument;

    Application(Container function, Container argument) {
        this.function = function;
        this.argument = argument;
        variables = null;
    }

    @Override
    public Set<Var> getVariables() {
        if (variables != null) {
            return variables;
        } else {
            Set<Var> res = new HashSet<>(getArgument().getVariables());
            res.addAll(getFunction().getVariables());
            return res;
        }
    }

    private Lambda getFunction() {
        return function.getLambda();
    }

    private Lambda getArgument() {
        return argument.getLambda();
    }

    @Override
    public boolean equals(Lambda other, VStack one, VStack two) {
        return other instanceof Application
                && getFunction().equals(((Application) other).getFunction(), one, two)
                && getArgument().equals(((Application) other).getArgument(), one, two);
    }

    @Override
    public int hashCode(VStack stack) {
        return 31 * getFunction().hashCode(stack) + getArgument().hashCode(stack);
    }

    @Override
    public String toString() {
        return getFunction() instanceof Abstraction
                ? "(" + getFunction().toString() + ")"
                : getFunction().toString()
                + " "
                + (getArgument() instanceof Reference
                ? getArgument().toString()
                : "(" + getArgument() + ")");
    }

    @Override
    public Lambda sub(Var varSubst, Container subst) {
        Container funcSubst = function.sub(varSubst, subst);
        Container argSubst = argument.sub(varSubst, subst);
        if (funcSubst == null || argSubst == null) {
            return null;
        }
        return new Application(funcSubst, argSubst);
    }

    @Override
    public Lambda reduceLambda() {
        if (function instanceof Abstraction) {
            Container res
                    = ((Abstraction) function).getBodyContainer()
                    .sub(((Abstraction) function).getVariable(),
                            getArgument().comp());
            return res == null ? null : res.getLambda();
        }
        if (function instanceof Computation) {
            Lambda funcInt = function.getLambda();
            if (funcInt instanceof Abstraction) {
                Container res
                        = ((Abstraction) funcInt).getBodyContainer()
                        .sub(((Abstraction) funcInt).getVariable(),
                                getArgument().comp(), null, null);
                return res == null ? null : res.getLambda();
            }
        }
        Container funcReduced = function.reduceLambda();
        if (funcReduced != null) {
            return funcReduced == (function) ? this : new Application(funcReduced, argument);
        }

        Container argReduced = argument.reduceLambda();
        return argReduced != null ? argReduced == (argument) ? this : new Application(function, argReduced) : null;
    }

    @Override
    public Container sub(Var varSubst,
                         Container subst,
                         VStack oldVariableStack,
                         VStack newVariableStack) {
        Container funcSubst = function.sub(varSubst,
                subst, oldVariableStack, newVariableStack);
        Container argSubst = argument.sub(varSubst,
                subst, oldVariableStack, newVariableStack);
        if (funcSubst == null || argSubst == null) {
            return null;
        }
        return new Application(funcSubst, argSubst);
    }

    @Override
    public Type toType(Manager manager) {
        Type funcType = getFunction().toType(manager);
        Type argType = getArgument().toType(manager);
        if (funcType == null || argType == null) {
            return null;
        }
        Type resType = manager.createType();
        return funcType.unifyWith(manager.createType(new Function(argType, resType)))
                ? resType
                : null;
    }
}

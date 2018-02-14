package lambdas;

import types.Type;
import types.Manager;

import java.util.HashSet;
import java.util.Set;

public class Reference extends Lambda {

    private final Var variable;

    Reference(Var variable) {
        this.variable = variable;
        this.variables = null;
    }

    @Override
    public Set<Var> getVariables() {
        Set<Var> res = variables;
        if (res == null) {
            res = new HashSet<>();
            res.add(variable);
        }
        return res;
    }

    @Override
    public boolean equals(Lambda other, VStack yourStack, VStack theirStack) {
        if (other instanceof Reference) {
            while (yourStack != null && theirStack != null) {
                if (variable == (yourStack.getVariable())
                        && ((Reference) other).variable == (theirStack.getVariable())) {
                    return true;
                }
                yourStack = yourStack.getPrevious();
                theirStack = theirStack.getPrevious();
            }
            return variable == (((Reference) other).variable);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(VStack stack) {
        int variableStackPosition = 0;
        while (stack != null) {
            if (stack.getVariable() == ((variable))) {
                return variableStackPosition;
            }
            stack = stack.getPrevious();
            variableStackPosition++;
        }
        return variable.hashCode();
    }

    @Override
    public String toString() {
        return variable.toString();
    }

    @Override
    public Container sub(Var varSubst, Container subst) {
        return varSubst == variable ? subst : this;
    }

    @Override
    public Container sub(Var varSubst,
                         Container subst,
                         VStack oldStack,
                         VStack newStack) {
        if (varSubst == variable) {
            return subst;
        }
        VStack old = oldStack;
        VStack neww = newStack;
        while (old != null) {
            if (old.getVariable() == variable) {
                return new Reference(neww.getVariable());
            } else {
                old = old.getPrevious();
                neww = neww.getPrevious();
            }
        }
        return this;
    }

    @Override
    public Type toType(Manager manager) {
        return manager.typeOf(this.variable).monoType();
    }
}

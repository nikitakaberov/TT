package lambdas;

import types.Function;
import types.Type;
import types.Manager;

import java.util.HashSet;
import java.util.Set;

public class Abstraction extends Lambda {
    private final Var variable;
    private final Container body;

    private Lambda getBody() {
        return body.getLambda();
    }

    Abstraction(Var variable, Container body) {
        this.variable = variable;
        this.body = body;
        this.variables = null;
    }

    @Override
    public Set<Var> getVariables() {
        if (variables != null) {
            return variables;
        } else {
            Set<Var> res = new HashSet<>(getBody().getVariables());
            res.remove(variable);
            return res;
        }
    }

    @Override
    public boolean equals(Lambda other, VStack yourVariableStack, VStack theirVariableStack) {
        return other instanceof Abstraction
                && getBody().equals(((Abstraction) other).getBody(),
                new VStack(variable, yourVariableStack),
                new VStack(((Abstraction) other).variable, theirVariableStack));
    }

    @Override
    public int hashCode(VStack stack) {
        return getBody().hashCode(new VStack(variable, stack));
    }

    private boolean containsVarSubst(Var variableSubst, Container subst) {
        if (!getBody().getVariables().contains(variableSubst)) {
            return false;
        } else {
            final boolean[] isVarIn = {false};
            subst.getLambda()
                    .getVariables()
                    .forEach((var) -> isVarIn[0] |= var.toString().equals(variable.toString()));
            return isVarIn[0];
        }
    }

    @Override
    public Container sub(Var varSubst, Container subst) {
        if (containsVarSubst(varSubst, subst)) {
            return null;
        } else {
            Container bodySubst = body.sub(varSubst, subst);
            return bodySubst == null ? null : new Abstraction(variable, bodySubst);
        }
    }

    @Override
    public Lambda reduceLambda() {
        Container res = body.reduceLambda();
        return res == null ? null : res == body ? this : new Abstraction(variable, res);
    }

    @Override
    public String toString() {
        return "\\" + variable.toString() + "." + getBody();
    }

    @Override
    public Container sub(Var varSubst,
                         Container subst,
                         VStack oldVariableStack,
                         VStack newVariableStack) {
        if (containsVarSubst(varSubst, subst)) {
            return null;
        } else {
            Var newParam = new Var(variable.toString());
            Container bodySubst = body.sub(varSubst, subst,
                    new VStack(variable, oldVariableStack),
                    new VStack(newParam, newVariableStack));
            return bodySubst == null ? null : new Abstraction(newParam, bodySubst);
        }
    }

    @Override
    public Type toType(Manager manager) {
        Type bodyType = getBody().toType(manager);
        return bodyType == null
                ? null
                : manager.createType(new Function(
                manager.typeOf(this.variable).getType(), bodyType
        ));
    }

    public Container getBodyContainer() {
        return body;
    }

    public Var getVariable() {
        return variable;
    }
}

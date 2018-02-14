package lambdas;

import types.Types;
import types.Type;
import types.Manager;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Let extends Lambda {

    private final Var variable;
    private final Container definitionContainer;
    private final Container exprContainer;

    Let(Var variable, Container definition, Container expr) {
        this.variable = variable;
        this.definitionContainer = definition;
        this.exprContainer = expr;
        this.variables = new HashSet<>(getDef().getVariables());
        this.variables.addAll(getExpr().getVariables());
        this.variables.remove(variable);
    }

    @Override
    public Set<Var> getVariables() {
        if (variables == null) {
            variables = new HashSet<>(getDef().getVariables());
            variables.addAll(getExpr().variables);
            variables.remove(variable);
        }
        return variables;
    }

    private Lambda getDef() {
        return definitionContainer.getLambda();
    }

    private Lambda getExpr() {
        return exprContainer.getLambda();
    }

    @Override
    public boolean equals(Lambda other, VStack yourVariableStack, VStack theirVariableStack) {
        return other instanceof Let &&
                getDef().equals(((Let) other).getDef(), yourVariableStack, theirVariableStack) &&
                getExpr().equals(((Let) other).getExpr(),
                        new VStack(variable, yourVariableStack),
                        new VStack(((Let) other).variable, theirVariableStack));
    }

    @Override
    public int hashCode(VStack stack) {
        return 32 * getDef().hashCode(stack)
                + getExpr().hashCode(new VStack(variable, stack));
    }

    private boolean helper(Var varSubst, Container subst) {
        if (getExpr().getVariables().contains(varSubst)) {
            final boolean[] isVarIn = {false};
            subst.getLambda()
                    .getVariables()
                    .forEach(var -> isVarIn[0] |= var.toString().equals(variable.toString()));
            return isVarIn[0];
        }
        return false;
    }

    @Override
    public Lambda sub(Var varSubst, Container subst) {
        if (helper(varSubst, subst)) {
            return null;
        }
        Container definitionSubst = definitionContainer.sub(varSubst, subst);
        if (definitionSubst == null) {
            return null;
        }
        Container exprSubst = exprContainer.sub(varSubst, subst);
        return exprSubst == null ? null : new Let(variable, definitionSubst, exprSubst);
    }

    @Override
    public Lambda reduceLambda() {
        Container substituted = exprContainer.sub(variable, getDef().comp());
        if (substituted != null) {
            return substituted.getLambda();
        }
        Lambda defReduced = getDef().reduceLambda();
        return defReduced == null ? null : defReduced.equals(getDef()) ? this : new Let(variable, defReduced, getExpr());
    }

    @Override
    public Container sub(Var varSubst,
                         Container subst,
                         VStack oldVariableStack,
                         VStack newVariableStack) {
        if (helper(varSubst, subst)) {
            return null;
        }
        Var newVar = new Var(variable.toString());
        Container definitionSubst = definitionContainer.sub(
                varSubst, subst, oldVariableStack, newVariableStack
        );
        if (definitionSubst == null) {
            return null;
        }
        Container exprSubst = exprContainer.sub(
                varSubst, subst, new VStack(varSubst, oldVariableStack), new VStack(newVar, newVariableStack)
        );
        return exprSubst == null ? null : new Let(newVar, definitionSubst, exprSubst);
    }

    @Override
    public Type toType(Manager manager) {
        Type defType = getDef().toType(manager);
        if (defType == null) {
            return null;
        }
        Set<Type> polymorphicTypes = defType.getVariables().stream().filter(type -> {
            for (Var it : getDef().getVariables()) {
                if (manager.typeOf(it).getType().contains(type)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toSet());
        manager.getVarTypes().put(variable, new Types(defType, polymorphicTypes));
        return getExpr().toType(manager);
    }
}

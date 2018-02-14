package lambdas;

import common.AScope;
import common.Structure;
import gen.LambdaLexer;
import gen.LambdaParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import static common.Generators.varGenerator;

public class LambdaStructure {

    public static Structure abstraction(String alias, Structure body) {
        return (scope, flag) -> {
            Var var = new Var(flag ? varGenerator.nextName() : alias);
            return new Abstraction(var, body.solve(new AScope(alias, var, scope), flag));
        };
    }

    public static Structure application(Structure func, Structure arg) {
        return (scope, flag) -> new Application(func.solve(scope, flag), arg.solve(scope, flag));
    }

    public static Structure variable(String name) {
        return (scope, flag) -> new Reference(scope.find(name, flag));
    }

    public static Structure let(String var, Structure def, Structure expr) {
        return (scope, flag) -> {
            Var v = new Var(flag ? varGenerator.nextName() : var);
            Lambda d = def.solve(scope, flag);
            return new Let(v, d, expr.solve(new AScope(var, v, scope), flag));
        };
    }

    public static Structure toLambdaStructure(String str) {
        //noinspection deprecation
        return new LambdaParser(
                new CommonTokenStream(new LambdaLexer(new ANTLRInputStream(str)))
        ).let_expression().ret;
    }
}

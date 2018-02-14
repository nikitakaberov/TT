package lambdas;

public class VStack {

    private final Var variable;
    private final VStack previous;

    VStack(Var variable, VStack previous) {
        this.variable = variable;
        this.previous = previous;
    }

    public Var getVariable() {
        return variable;
    }

    public VStack getPrevious() {
        return previous;
    }
}

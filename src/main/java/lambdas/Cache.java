package lambdas;

class Cache {

    private final Var variable;
    private final Container subst;

    Cache(Var variable, Container subst) {
        this.variable = variable;
        this.subst = subst;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Cache
                && variable == ((Cache) other).variable
                && subst.equals(((Cache) other).subst);
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}

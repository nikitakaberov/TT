package lambdas;

import java.util.HashMap;
import java.util.Map;

public class Computation implements Container {

    private Lambda li;
    private boolean full = false;
    private final Map<Cache, Container> cache = new HashMap<>();

    Computation(Lambda li) {
        this.li = li;
    }

    @Override
    public Lambda getLambda() {
        return li;
    }

    @Override
    public Container reduceLambda() {
        if (full) {
            return null;
        }
        Lambda l = li.reduceLambda();
        if (l == null) {
            full = true;
            return null;
        } else {
            li = l;
            cache.clear();
            return this;
        }
    }

    @Override
    public Container sub(Var var, Container subst) {
        return sub(var, subst, null, null);
    }

    @Override
    public Container sub(Var varSubst,
                         Container subst,
                         VStack oldVariableStack,
                         VStack newVariableStack) {
        Cache cached = new Cache(varSubst, subst);
        if (cache.containsKey(cached)) {
            return cache.get(cached);
        } else {
            Container result = li.sub(varSubst, subst, oldVariableStack, newVariableStack);
            Container substituted = result == null ? null : result.comp();
            cache.put(cached, substituted);
            return substituted;
        }
    }

    @Override
    public Container comp() {
        return this;
    }
}

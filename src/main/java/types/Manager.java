package types;

import lambdas.Var;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Manager {

    private final Map<Type, Descriptor> descriptors = new HashMap<>();
    private final Map<Var, Types> varTypes = new HashMap<>();

    public Descriptor getDescriptor(Type type) {
        return descriptors.get(type.backingType());
    }

    public Type createType() {
        return new Type(this);
    }

    public Type createType(Descriptor descriptor) {
        Type type = new Type(this);
        if (descriptor != null) {
            descriptors.put(type, descriptor);
        }
        return type;
    }

    public void sub(Type type, Descriptor typeDescriptor) {
        if (!typeDescriptor.contains(type)) {
            descriptors.put(type.backingType(), typeDescriptor);
        }
    }

    private boolean sub(Type type, Type substitutions) {
        Descriptor descriptor = substitutions.getDescriptor();
        if (descriptor != null && descriptor.contains(type)) {
            return false;
        }
        descriptors.remove(type.backingType());
        type.backingType().backType = substitutions.backingType();
        return true;
    }

    public Types typeOf(Var variable) {
        if (!varTypes.containsKey(variable)) {
            varTypes.put(variable, new Types(createType(), new HashSet<>()));
        }
        return varTypes.get(variable);
    }

    public Map<Var, Types> getVarTypes() {
        return varTypes;
    }

    public boolean equalize(Type first, Type second, boolean unify) {
        if (first.backingType() == second.backingType()) {
            return true;
        }

        Descriptor one = getDescriptor(first);
        Descriptor two = getDescriptor(second);

        if (one != null && two != null) {
            if (!one.equals(two)) {
                return false;
            }
            for (int i = 0; i < one.getParameters().size(); i++) {
                if (!equalize(one.getParameters().get(i),
                        two.getParameters().get(i), unify)) {
                    return false;
                }
            }
            if (unify) {
                descriptors.remove(first.backingType());
                first.backingType().backType = second.backingType();
            }
            return true;
        }
        return unify
                && (one == null
                ? sub(first, second)
                : sub(second, first));
    }
}

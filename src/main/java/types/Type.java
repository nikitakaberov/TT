package types;

import common.Generator;

import java.util.*;

import static common.Generators.typeGenerator;

public class Type {

    Type backType = this;

    private final Manager manager;

    public Type(Manager manager) {
        this.manager = manager;
    }

    Type backingType() {
        if (this != backType) {
            backType = backType.backingType();
        }
        return backType;
    }

    public Descriptor getDescriptor() {
        return manager.getDescriptor(this);
    }

    @Override
    public String toString() {
        return getDescriptor() == null
                ? "type" + Integer.toHexString(backingType().hashCode())
                : getDescriptor().toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Type ? eq((Type) obj) : super.equals(obj);
    }

    private boolean eq(Type other) {
        return manager == other.manager && manager.equalize(this, other, false);
    }

    private void concreteType(Generator generator) {
        Descriptor descriptor = getDescriptor();
        if (descriptor != null) {
            descriptor.getParameters().forEach(it -> it.concreteType(generator));
        } else {
            manager.sub(this, new Const(generator.nextName()));
        }
    }

    public boolean contains(Type other) {
        Descriptor descriptor = getDescriptor();
        return this.equals(other) || descriptor != null && descriptor.contains(other);
    }

    public String left() {
        return getDescriptor() instanceof Function ? "(" + toString() + ")" : toString();
    }

    private void countVariable(Set<Type> variables) {
        Descriptor descriptor = getDescriptor();
        if (descriptor == null) {
            variables.add(this.backingType());
        } else {
            descriptor.getParameters().forEach(it -> it.countVariable(variables));
        }
    }

    public Set<Type> getVariables() {
        Set<Type> vars = new HashSet<>();
        countVariable(vars);
        return vars;
    }

    public boolean unifyWith(Type other) {
        return manager == other.manager
                && manager.equalize(this, other, true);
    }

    public void concreteType() {
        concreteType(typeGenerator);
    }

    public Type recreateLiterals(Set<Type> literals, Map<Type, Type> createdLiterals) {
        Descriptor descriptor = getDescriptor();
        if (descriptor == null) {
            if (literals.contains(backingType())) {
                literals.remove(backingType());
                Type lit = manager.createType();
                createdLiterals.put(backingType(), lit);
                return lit;
            }
            return createdLiterals.get(backingType()) == null
                    ? this
                    : createdLiterals.get(backingType());
        } else {
            boolean changed = false;
            ArrayList<Type> newParams = new ArrayList<>();
            for (Type it : descriptor.getParameters()) {
                Type newParam = it.recreateLiterals(literals, createdLiterals);
                newParams.add(newParam);
                if (it != newParam) {
                    changed = true;
                }
            }
            return changed ? manager.createType(descriptor.copy(newParams)) : this;
        }
    }
}

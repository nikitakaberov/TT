package types;

import java.util.ArrayList;

public abstract class Descriptor {

    private final ArrayList<Type> parameters;

    Descriptor(ArrayList<Type> parameters) {
        this.parameters = parameters;
    }

    public abstract Descriptor copy(ArrayList<Type> parameters);

    public abstract boolean equals(Descriptor other);

    public boolean contains(Type other) {
        return parameters.stream().anyMatch(parameter -> parameter.contains(other));
    }

    public ArrayList<Type> getParameters() {
        return parameters;
    }
}

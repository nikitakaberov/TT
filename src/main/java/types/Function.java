package types;

import java.util.ArrayList;

public class Function extends Descriptor {

    public Function(Type argument, Type result) {
        super(new ArrayList<>());
        getParameters().add(argument);
        getParameters().add(result);
    }

    @Override
    public String toString() {
        return getParameters().get(0).left() + " -> " + getParameters().get(1).toString();
    }

    @Override
    public Descriptor copy(ArrayList<Type> parameters) {
        return new Function(parameters.get(0), parameters.get(1));
    }

    @Override
    public boolean equals(Descriptor other) {
        return other instanceof Function;
    }

}

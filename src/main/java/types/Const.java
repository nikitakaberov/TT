package types;

import java.util.ArrayList;

public class Const extends Descriptor {

    private final String type;

    Const(String type) {
        super(new ArrayList<>());
        this.type = type;
    }

    @Override
    public boolean equals(Descriptor other) {
        return other instanceof Const && type.equals(((Const) other).type);
    }

    @Override
    public Descriptor copy(ArrayList<Type> parameters) {
        return new Const(type);
    }

    @Override
    public String toString() {
        return type;
    }

}

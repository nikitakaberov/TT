package types;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Types {
    private final Type type;
    private final Set<Type> types;

    public Types(Type type, Set<Type> types) {
        this.type = type;
        this.types = types;
    }

    public Type monoType() {
        return types.isEmpty()
                ? getType()
                : type.recreateLiterals(
                types.stream().map(Type::backingType).collect(Collectors.toSet()),
                new HashMap<>()
        );
    }

    public Type getType() {
        return type;
    }
}

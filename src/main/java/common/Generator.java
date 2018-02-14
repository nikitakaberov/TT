package common;

public class Generator {
    private final boolean isVar;
    private long index;

    Generator(boolean isVar) {
        this.isVar = isVar;
        this.index = 0;
    }

    public String nextName() {
        String nextName = (this.isVar ? "a" : "'a") + index;
        index++;
        return nextName;
    }
}

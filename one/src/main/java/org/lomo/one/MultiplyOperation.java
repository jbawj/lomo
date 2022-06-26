package org.lomo.one;

public class MultiplyOperation implements Operation{
    @Override
    public boolean isApplicable(long i) {
        return true;
    }

    @Override
    public long calc(long i) {
        return i << 1;
    }

    @Override
    public String toString() {
        return "mult";
    }
}

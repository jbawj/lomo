package org.lomo.one;

public class DivideOperation implements Operation{

    @Override
    public boolean isApplicable(long i) {
        return (i & 1) == 0;
    }

    @Override
    public long calc(long i) {
        return i >> 1;
    }

    @Override
    public String toString() {
        return "div";
    }
}

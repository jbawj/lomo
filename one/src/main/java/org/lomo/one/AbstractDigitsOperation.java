package org.lomo.one;

abstract public class AbstractDigitsOperation implements Operation {
    @Override
    public boolean isApplicable(long i) {
        int length = String.valueOf(i).length();
        return (length & 1) == 0;
    }
}

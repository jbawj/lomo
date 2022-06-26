package org.lomo.one;

public class TakeRightOperation extends AbstractDigitsOperation {
    @Override
    public long calc(long i) {
        String s = String.valueOf(i);

        return Long.parseLong(s.substring(s.length() / 2));
    }

    @Override
    public String toString() {
        return "takeRight";
    }
}

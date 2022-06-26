package org.lomo.one;

public class TakeLeftOperation extends AbstractDigitsOperation {
    @Override
    public long calc(long i) {
        String s = String.valueOf(i);

        return Long.parseLong(s.substring(0, s.length() / 2));
    }

    @Override
    public String toString() {
        return "takeLeft";
    }
}

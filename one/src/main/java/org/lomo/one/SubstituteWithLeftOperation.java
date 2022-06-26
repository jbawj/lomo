package org.lomo.one;

public class SubstituteWithLeftOperation extends AbstractDigitsOperation {

    @Override
    public boolean isApplicable(long i) {
        boolean superApplicable = super.isApplicable(i);
        if (superApplicable) {
            long calc = calc(i);

            return calc != i;
        }

        return false;
    }

    @Override
    public long calc(long i) {
        String s = String.valueOf(i);
        String substring = s.substring(0, s.length() / 2);
        return Long.parseLong(substring + substring);
    }

    @Override
    public String toString() {
        return "subLeft";
    }
}

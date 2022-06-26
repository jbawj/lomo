package org.lomo.one;

public interface Operation {
    boolean isApplicable(long i);
    long calc(long i);
}

package org.assigment.services;

public interface AntiPatternDetector {
    boolean hasAntiPattern();
    int getAntiPatternCount();
    void clearPatternCount(); // for throw in finally code chunk becuase of static value;
}

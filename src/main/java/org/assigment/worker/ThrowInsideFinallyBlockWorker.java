package org.assigment.worker;

import org.assigment.services.AntiPatternDetector;
import org.assigment.services.AntiPatternDetectorFactory;
import org.assigment.services.DetectorType;
import org.assigment.services.ThrowInsideFinallyAntiPatternDetector;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

public class ThrowInsideFinallyBlockWorker implements Callable {
    private AntiPatternDetector antiPatternDetector;
    public ThrowInsideFinallyBlockWorker(String fileName) throws FileNotFoundException {
        this.antiPatternDetector = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_INSIDE_FINAL,fileName);
    }
    @Override
    public Object call() {
        this.antiPatternDetector.hasAntiPattern();

        return this.antiPatternDetector.getAntiPatternCount();
    }

    public void clearPatternCount() {
        this.antiPatternDetector.clearPatternCount();
    }
}

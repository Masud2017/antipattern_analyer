package org.assigment.worker;

import org.assigment.services.AntiPatternDetector;
import org.assigment.services.AntiPatternDetectorFactory;
import org.assigment.services.DetectorType;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

public class ThrowInKitchenSinkWorker implements Callable {
    private AntiPatternDetector antiPatternDetector;

    public ThrowInKitchenSinkWorker(String filename) throws FileNotFoundException {
        this.antiPatternDetector = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_KITCHEN_SINK,filename);
    }

    @Override
    public Object call() {
        this.antiPatternDetector.hasAntiPattern();
        return this.antiPatternDetector.getAntiPatternCount();
    }
}

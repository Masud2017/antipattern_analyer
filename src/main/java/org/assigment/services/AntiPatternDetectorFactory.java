package org.assigment.services;

import java.io.FileNotFoundException;

public class AntiPatternDetectorFactory {
    private AntiPatternDetectorFactory(){}

    public static AntiPatternDetector getInstance(DetectorType detectorType,String filename) throws FileNotFoundException {
        switch (detectorType) {
            case THROW_INSIDE_FINAL:
                return new ThrowInsideFinallyAntiPatternDetector(filename);
            case THROW_KITCHEN_SINK:
                return new ThrowKitchenSinkAntiPatternDetector(filename);

        }
        return null;
    }
}

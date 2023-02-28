package org.assigment;

import org.assigment.services.AntiPatternDetector;
import org.assigment.services.AntiPatternDetectorFactory;
import org.assigment.services.DetectorType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SynchronizedAntiPatternDetector {
    private List<String> filePathList;
    private List<Integer> throwInsideFinallyResultList = new ArrayList<>();
    private List<Integer> throwKitchenSinkResultList = new ArrayList<>();

    public SynchronizedAntiPatternDetector(List<String> filePathList) {
        this.filePathList = filePathList;
    }


    public void performComputation () throws FileNotFoundException {
        // throw in finally block
        for (String filePathItem : this.filePathList) {
            AntiPatternDetector antiPatternDetectorThrowInFinally = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_INSIDE_FINAL,filePathItem);
            antiPatternDetectorThrowInFinally.hasAntiPattern();
            this.throwInsideFinallyResultList.add(antiPatternDetectorThrowInFinally.getAntiPatternCount());
            antiPatternDetectorThrowInFinally.clearPatternCount();
        }

        // throw in kitchen sink
        for (String filePathItem : this.filePathList) {
            AntiPatternDetector antiPatternDetectorThrowInKitchenSink = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_KITCHEN_SINK,filePathItem);
            antiPatternDetectorThrowInKitchenSink.hasAntiPattern();
            this.throwKitchenSinkResultList.add(antiPatternDetectorThrowInKitchenSink.getAntiPatternCount());
        }
    }

    public Integer getTotalThrowInsideFinallyAntiPattern(){
        Integer totalCounter = 0;

        for (Integer item : this.throwInsideFinallyResultList) {
            totalCounter += item;
        }

        return totalCounter;
    }

    public Integer getTotalThrowInKitchenAntiPattern() {
        Integer totalCounter2 = 0;
        for (Integer item: this.throwKitchenSinkResultList) {
            totalCounter2 += item;
        }

        return totalCounter2;
    }
}

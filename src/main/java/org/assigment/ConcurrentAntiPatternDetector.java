package org.assigment;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.assigment.worker.ThrowInKitchenSinkWorker;
import org.assigment.worker.ThrowInsideFinallyBlockWorker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentAntiPatternDetector {
    private ReentrantLock lock = new ReentrantLock();
    private ExecutorService executorServiceThrowInsideFinally = Executors.newFixedThreadPool(10);
    private ExecutorService executorServiceThrowInKitchenSink = Executors.newFixedThreadPool(10);
    private List<String> filePathList;
    private List<Future<Integer>> throwInsideFinallyFutureList = new ArrayList<>();
    private List<Future<Integer>> throwInKitchenSinkFutureList = new ArrayList<>();

    public ConcurrentAntiPatternDetector(List<String> filePathList) {
        this.filePathList = filePathList;
    }

    public void performComputation () throws FileNotFoundException, InterruptedException {
        // getting throw inside finally future list
        for (String pathItem : this.filePathList) {
            ThrowInsideFinallyBlockWorker finallyBlockWorker = new ThrowInsideFinallyBlockWorker(pathItem);
//            Future<Integer> futureItem = this.executorServiceThrowInsideFinally.submit(finallyBlockWorker);
            this.throwInsideFinallyFutureList.add(this.executorServiceThrowInsideFinally.submit(finallyBlockWorker));
//            finallyBlockWorker.clearPatternCount();
        }

        // getting throw in kitchen sink list
        for (String pathItem : this.filePathList) {
            ThrowInKitchenSinkWorker kitchenSinkWorker = new ThrowInKitchenSinkWorker(pathItem);
            this.throwInKitchenSinkFutureList.add(this.executorServiceThrowInKitchenSink.submit(kitchenSinkWorker));
        }

    }

    public Integer getTotalThrowInsideFinallyAntiPattern() throws ExecutionException, InterruptedException {
        Integer totalCount = 0;
        for (Future<Integer> futureItem : this.throwInsideFinallyFutureList) {
            totalCount += futureItem.get();
//            System.out.print("Inspecting the value of totalCount = "+totalCount);

        }

        return totalCount;
    }

    public Integer getTotalThrowInKitchenAntiPattern() throws ExecutionException, InterruptedException {
        Integer totalCount = 0;

        for (Future<Integer> futureItem : this.throwInKitchenSinkFutureList) {
            totalCount += futureItem.get();
        }

        return totalCount;
    }

    public boolean close() {
        try {
            this.throwInKitchenSinkFutureList.clear();
            this.throwInsideFinallyFutureList.clear();
            this.executorServiceThrowInsideFinally.shutdown();
            this.executorServiceThrowInKitchenSink.shutdown();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

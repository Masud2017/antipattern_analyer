package org.assigment;

import com.google.common.io.Resources;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.assigment.services.AntiPatternDetector;
import org.assigment.services.AntiPatternDetectorFactory;
import org.assigment.services.DetectorType;
import org.assigment.services.ThrowInsideFinallyAntiPatternDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class App 
{
    static List<String> filePathList = new ArrayList<>();

    public static void main( String[] args ) throws IOException, MojoExecutionException, MojoFailureException, ExecutionException, InterruptedException {
//        String filename = Resources.getResource("KitchenSinkExample.java").getPath().toString();
//
//        AntiPatternDetector antiPatternDetector = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_INSIDE_FINAL,filename);
//        antiPatternDetector.hasAntiPattern();
//
//        System.out.println("Count of throw inside finally block count : "+antiPatternDetector.getAntiPatternCount());
//
//
//
////
//        String filename2 = Resources.getResource("KitchenSinkExample.java").getPath().toString();
//
//        AntiPatternDetector kitchenSinkAntiPattern = AntiPatternDetectorFactory.getInstance(DetectorType.THROW_KITCHEN_SINK,filename2);
//
//        kitchenSinkAntiPattern.hasAntiPattern();
//
//        System.out.println("Number of kitchen sink anti pattern occurrence : "+kitchenSinkAntiPattern.getAntiPatternCount());
// FIXME: 2/24/2023
//
        String sourceFolderPath = System.getProperty("sourceFolder");
        File sourceFolder = new File(sourceFolderPath);
        File[] javaFiles = sourceFolder.listFiles();

        List<String> filePathList = listAllFiles(javaFiles);


        System.out.println("=============================================================================================");
        ConcurrentAntiPatternDetector concurrentAntiPatternDetector = new ConcurrentAntiPatternDetector(filePathList);
        concurrentAntiPatternDetector.performComputation();
//        SynchronizedAntiPatternDetector synchronizedAntiPatternDetector = new SynchronizedAntiPatternDetector(filePathList);
//        synchronizedAntiPatternDetector.performComputation();


        Integer totalThrowInsideFinally = concurrentAntiPatternDetector.getTotalThrowInsideFinallyAntiPattern();
        Integer totalThrowInKitchen = concurrentAntiPatternDetector.getTotalThrowInKitchenAntiPattern();
//        Integer totalThrowInsideFinally = synchronizedAntiPatternDetector.getTotalThrowInsideFinallyAntiPattern();
//        Integer totalThrowInKitchen = synchronizedAntiPatternDetector.getTotalThrowInKitchenAntiPattern();

        System.out.println("Total throw inside finally anti pattern : "+ totalThrowInsideFinally);
        System.out.println("Total throw in kitchen anti pattern : "+totalThrowInKitchen);

        concurrentAntiPatternDetector.close();
    }

    public static List<String> listAllFiles(File[] fileList) {
//        Logger logger= LoggerFactory.getLogger(App.class);

        for (File fileItem : fileList) {
            System.out.println("File info Name : "+fileItem.getName()+" size ; "+fileItem.getTotalSpace());



            if (fileItem.getName().endsWith(".java")) {
                System.out.println(fileItem.getName());


                filePathList.add(fileItem.getPath());
            }
            if (fileItem.isDirectory()) {
                listAllFiles(fileItem.listFiles());

            }
        }

        return filePathList;
    }
}

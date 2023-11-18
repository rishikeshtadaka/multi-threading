package org.example.multi.thread.fair.load;

import org.example.multi.thread.unequal.load.MathUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadFairLoadMain {
    private static Integer tillNumber=10000000;
    private static Integer counter=0;
    private static Integer noOfThreads=4;
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        System.out.println("Available cores:"+Runtime.getRuntime().availableProcessors());
        System.out.println("*********************Started processing***********************");
        Integer batchSize=tillNumber/noOfThreads;
        ExecutorService executorService= Executors.newFixedThreadPool(noOfThreads);
        MathUtilFailLoad[] mathUtils = new MathUtilFailLoad[noOfThreads];
        for(Integer i=0;i<noOfThreads;i++){
            MathUtilFailLoad mathUtil=new MathUtilFailLoad(tillNumber);
            mathUtils[i]=mathUtil;
            executorService.execute(mathUtil);
        }
        executorService.shutdown();


        // Wait for all threads to finish
        while (!executorService.isTerminated()) {
            ////Do nothing
        }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long totalMilliSeconds= TimeUnit.NANOSECONDS.toMillis(totalTime);
        long totalSeconds= TimeUnit.NANOSECONDS.toSeconds(totalTime);

        System.out.println("Total prime numbers:"+MathUtilFailLoad.counter);
        System.out.println("Total time(non-seconds):"+totalTime);
        System.out.println("Total time(milli-seconds):"+totalMilliSeconds);
        System.out.println("Total time(seconds):"+totalSeconds);
        System.out.println("*********************Completed processing***********************");
    }
}

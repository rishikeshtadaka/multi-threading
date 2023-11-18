package org.example.multi.thread;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadMain {
    private static Integer tillNumber=10000000;
    private static Integer counter=0;
    private static Integer noOfThreads=4;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("*********************Started processing***********************");
        Integer batchSize=tillNumber/noOfThreads;
        ExecutorService executorService= Executors.newFixedThreadPool(noOfThreads);
        MathUtil[] mathUtils = new MathUtil[noOfThreads];
        for(Integer i=0;i<noOfThreads;i++){
            MathUtil mathUtil=new MathUtil(i*batchSize+1,(i+1)*batchSize);
            mathUtils[i]=mathUtil;
            executorService.execute(mathUtil);
        }
        executorService.shutdown();


        // Wait for all threads to finish
        while (!executorService.isTerminated()) {
            // Do nothing
        }

        int totalPrimes = 0;
        for (MathUtil checker : mathUtils) {
            totalPrimes += checker.getCounter();
        }


        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long totalMilliSeconds= TimeUnit.NANOSECONDS.toMillis(totalTime);
        long totalSeconds= TimeUnit.NANOSECONDS.toSeconds(totalTime);

        System.out.println("Total prime numbers:"+totalPrimes);
        System.out.println("Total time(non-seconds):"+totalTime);
        System.out.println("Total time(milli-seconds):"+totalMilliSeconds);
        System.out.println("Total time(seconds):"+totalSeconds);
        System.out.println("*********************Completed processing***********************");
    }
}
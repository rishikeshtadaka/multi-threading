package org.example;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Integer tillNumber=10000000;
    private static Integer counter=0;
    private static void checkPrime(Integer number){
        for(Integer i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return;
            }
        }
        counter++;
    }
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("*********************Started processing***********************");
        for(Integer i=2;i<tillNumber;i++){
            checkPrime(i);
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long totalMilliSeconds= TimeUnit.NANOSECONDS.toMillis(totalTime);
        long totalSeconds= TimeUnit.NANOSECONDS.toSeconds(totalTime);

        System.out.println("Total prime numbers:"+counter);
        System.out.println("Total time(non-seconds):"+totalTime);
        System.out.println("Total time(milli-seconds):"+totalMilliSeconds);
        System.out.println("Total time(seconds):"+totalSeconds);
        System.out.println("*********************Completed processing***********************");
    }
}
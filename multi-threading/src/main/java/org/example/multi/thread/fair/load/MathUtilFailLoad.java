package org.example.multi.thread.fair.load;

import java.util.concurrent.TimeUnit;

public class MathUtilFailLoad extends Thread {
    public static Integer checkNumber=2;
    public static Integer maxNumber;
    public static Integer counter=0;
    public MathUtilFailLoad(Integer maxNumber){
        MathUtilFailLoad.maxNumber=maxNumber;
    }
    @Override
    public void run(){
        long startTime = System.nanoTime();
        while(checkNumber<maxNumber){
            if(checkPrime(checkNumber++)){
                counter++;
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long totalMilliSeconds= TimeUnit.NANOSECONDS.toMillis(totalTime);
        System.out.println("Time Taken(ms):"+totalMilliSeconds);
    }
    private boolean checkPrime(Integer number){
        for(Integer i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}

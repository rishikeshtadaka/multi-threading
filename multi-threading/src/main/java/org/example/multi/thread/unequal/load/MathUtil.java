package org.example.multi.thread.unequal.load;

import java.util.concurrent.TimeUnit;

public class MathUtil extends Thread {
    Integer maxNumber;
    Integer startNumber;
    public static Integer counter=0;
    public MathUtil(Integer startNumber,Integer maxNumber){
        this.maxNumber=maxNumber;
        this.startNumber=startNumber;
    }
    @Override
    public void run(){
        long startTime = System.nanoTime();
        for(Integer i=this.startNumber;i<=this.maxNumber;i++){
            if(this.checkPrime(i)){
                counter++;
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long totalMilliSeconds= TimeUnit.NANOSECONDS.toMillis(totalTime);
        System.out.println("start and end:"+this.startNumber+","+this.maxNumber+" counter:"+this.counter+" Time Taken(ms):"+totalMilliSeconds);
    }

    private boolean checkPrime(Integer number){
        for(Integer i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
       return true;
    }
    public Integer getCounter(){
        return this.counter;
    }
}

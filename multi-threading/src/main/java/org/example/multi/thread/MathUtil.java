package org.example.multi.thread;

public class MathUtil extends Thread {
    Integer counter=0;
    Integer maxNumber;
    Integer startNumber;
    public MathUtil(Integer startNumber,Integer maxNumber){
        this.maxNumber=maxNumber;
        this.startNumber=startNumber;
    }
    @Override
    public void run(){
        for(Integer i=this.startNumber;i<=this.maxNumber;i++){
            if(this.checkPrime(i)){
                counter++;
            }
        }
        System.out.println("start and end:"+this.startNumber+","+this.maxNumber+" counter:"+this.counter);
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

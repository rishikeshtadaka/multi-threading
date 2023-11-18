package org.example;

public class Main {
    private static Integer tillNumber=1000;
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
        for(Integer i=2;i<tillNumber;i++){
            checkPrime(i);
        }
        System.out.println("Total prime numbers:"+counter);
    }
}
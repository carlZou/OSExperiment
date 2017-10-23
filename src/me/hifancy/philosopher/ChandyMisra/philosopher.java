package me.hifancy.philosopher.ChandyMisra;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class philosopher implements Runnable {
    private stick leftStick , rightStick;
    private philosopher leftPhilo,rightPhilo;
    private int number , eatTimes;

    public philosopher(int number,int eatTimes){
        this.number = number;
        this.eatTimes = eatTimes;
    }

    public void eating(){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("catch an InterruptedException");
                return;
            }
        leftStick.setStuts(true);
        rightStick.setStuts(true);
        eatTimes++;
        System.out.println("philosopher" + number +"is eating");
    }

    public boolean answer(stick used){
        boolean retFlag = false;
        synchronized (this){
            if (used.getStatus()){
                if (used == leftStick)
                    used.setOwner(leftPhilo.number);
                else if (used == rightStick)
                    used.setOwner(rightPhilo.number);
                else {
                    System.out.println("Error status!");
                    retFlag = false;
                }
                used.setStuts(false);
                retFlag = true;
            }
            else
                retFlag = false;
        }
        if (retFlag)
            System.out.println("philoser" + number + "request successs!");
        return retFlag;
    }

    public void run(){
        Random r = new Random();
        int intR;

        while(true){
            while (leftStick.getOwner() != number | rightStick.getOwner() != number){
                intR = r.nextInt();
                if(intR % 2 == 0 && leftStick.getOwner() != number)
                    leftPhilo.answer(leftStick);
                else if(intR % 2 == 1 && rightStick.getOwner() != number)
                    rightPhilo.answer(rightStick);
            }
            synchronized(this){
                if(leftStick.getOwner() == number
                        && rightStick.getOwner() == number){
                    eating();
                }
            }
            try{
                int sleepSec = r.nextInt();
                if(sleepSec < 0)
                    sleepSec = -sleepSec;
                Thread.sleep(sleepSec % 500);
            }catch(InterruptedException ie){
                System.out.println("Catch an Interrupted Exception!");
            }
        }

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        philosopher[] ps = new philosopher[5];
        stick[] ss = new stick[5];
        for (int i = 0; i < 5 ; i++) {
            ss[i] = new stick(true, i);
            ps[i]= new philosopher(i,0);
        }
        System.out.println((0-1)%5);
        for (int i = 0; i < 5; i++) {
            ps[i].leftPhilo= ps[(i+4)%5];
            ps[i].rightPhilo= ps[(i+1)%5];
            ps[i].leftStick = ss[i];
            ps[i].rightStick=ss[(i+1)%5];
        }
        for (int i = 0; i < 5; i++) {
            exec.execute(ps[i]);
        }
        exec.shutdown();

    }
}


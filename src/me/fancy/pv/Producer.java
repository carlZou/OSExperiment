package me.fancy.pv;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread{
    private static String name;
    public Producer(String name){
        this.name = name;
    }

    private AtomicInteger i = new AtomicInteger(0);
    final int POOL = 100;
//    private ArrayList<prod> list = new ArrayList();
    @Override
    public void run() {
        synchronized (this) {
            while (prod.get() < POOL) {
                 proudce();
                 notifyAll();
                 System.out.println(this.getName()+"         produce + 1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void proudce(){
        prod.add();
    }

}

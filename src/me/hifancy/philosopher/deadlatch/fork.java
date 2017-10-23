package me.hifancy.philosopher.deadlatch;

public class fork {
    private boolean[] used = {false,false,false,false,false};

    public synchronized void takeFork(){
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        while (used[i] ){
                try {
                    wait();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
        }

        used[i] = true;
        used[(i+1)%5] = true;
    }

    public synchronized void putFork(){
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);

        used[i] = false;
        used[(i+1)%5] = false;
        notifyAll();
    }
}

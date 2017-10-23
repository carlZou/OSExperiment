package me.hifancy.philosopher.latch2;

public class fork {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {

        while (taken)

            wait();

        taken = true;

    }

    public synchronized void drop() {

        taken = false;

        notifyAll();

    }
}

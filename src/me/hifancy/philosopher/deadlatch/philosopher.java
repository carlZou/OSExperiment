package me.hifancy.philosopher.deadlatch;

public class philosopher extends Thread{
    private String name;
    private fork fork;
    public philosopher(String name,fork fork){
        super(name);
        this.fork = fork;
        this.name = name;
    }

    public void thinking(){
        System.out.println("I am thinking" + name);
        try {
            sleep(1000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public void eating(){
        System.out.println("I am eating" + name);
        try {
            sleep(1000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public void run(){
        while (true){
            thinking();
            fork.takeFork();
            eating();
            fork.putFork();
        }
    }
}

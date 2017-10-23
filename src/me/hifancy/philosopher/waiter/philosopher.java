package me.hifancy.philosopher.waiter;

public class philosopher implements Runnable{
    private static Waiter waiter = new Waiter();
    private int id ;

    public philosopher(int id){
        this.id = id;
    }

    public void eating(){
        System.out.println("eating" + id);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    private void thinking(){
        System.out.println("thinking" + id);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
    @Override
    public void run() {
        for(int i = 0;i<=15;i++){
            if (waiter.ask(id)){
                eating();
                waiter.put(id);
            }
            thinking();
        }
    }
}

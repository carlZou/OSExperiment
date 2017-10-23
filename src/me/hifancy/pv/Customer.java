package me.hifancy.pv;

public class Customer extends Thread{
    private  String name;

    public Customer(String name){
        this.name = name;
    }
    @Override
    public void run() {
        synchronized (this){
            while (true){
                prod.use();
                notifyAll();
                System.out.println(this.getName()+"   use-1");
                try{
                    Thread.sleep(50);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }

        }
    }
}

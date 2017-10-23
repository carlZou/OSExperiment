package me.hifancy.philosopher.waiter;

public class test {
    private static Thread[] threads = new Thread[5];
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i < 5; i ++){
            threads[i] = new Thread(new philosopher(i));
            threads[i].start();
        }
        //当前线程组活动线程数
        while(Thread.activeCount()>1){
            if(Waiter.monitor()){
                System.out.println("死锁!!!!!!!!!!!!!!!!");
            }
            try{
                Thread.sleep(100);
            }catch(InterruptedException ex){

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("運行時間" + "=" +(endTime-startTime));
    }

}

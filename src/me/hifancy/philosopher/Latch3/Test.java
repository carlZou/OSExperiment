package me.hifancy.philosopher.Latch3;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 通过模拟哲学家问题来观察死锁的产生
 * 下面的代码是模拟的五个哲学家和五根筷子的经典哲学家问题
 * */

//共享资源：筷子类
class Chopstick{
    private boolean token=false; //该筷子是否被使用了

    //使用筷子，如果该筷子已被别的线程使用，则当前线程调用wait()挂起
    public synchronized void take() throws InterruptedException{
        while(token){
            wait();
        }
        token=true;
    }

    //筷子使用完以后，放下筷子，并唤醒其它正在wait()的线程
    public synchronized void drop(){
        token=false;
        notifyAll();
    }
}

class Philosopers implements Runnable{

    //左边和右边的筷子
    private Chopstick left;
    private Chopstick right;

    private final int id;//哲学家使用筷子的编号
    private final int pauseFactor; //暂停因子
    private Random rand = new Random(200);

    public Philosopers(Chopstick left,Chopstick right,int id,int pauseFactor){
        this.left=left;
        this.right=right;
        this.id=id;
        this.pauseFactor=pauseFactor;
    }

    //暂停随机时间
    private void pause() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(pauseFactor*rand.nextInt(100));
    }

    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println(this+" "+"thinking........... ");//表示正在思考
                pause();//模拟思考时间
                System.out.println(this+" 取得左边的筷子");
                left.take(); //取得左边的筷子
                System.out.println(this+" 取得右边的筷子");
                right.take();
                System.out.println(this+"Eating...........");//就餐
                pause();//模拟就餐时间

                //放下筷子
                left.drop();
                right.drop();
            }
        }catch(InterruptedException ex){
            System.out.println(this+" 通过中断异常退出");
        }
    }

    public String toString(){
        return (id+1)+"号哲学家 ";
    }
}

public class Test {

    public static void main(String[] args) throws Exception{

        //可以通过调整pauseFactor从而调整哲学家思考的时间
        //思考时间越短则线程间对共享资源的竞争越强越容易产生死锁问题
        //pauseFacotr等于0的时候几乎每次都可以看到死锁问题的产生
        int pauseFactor=0;
        int size=5;
        Chopstick[] chopstick = new Chopstick[size];

        //五只筷子
        for(int i=0;i<size;i++){
            chopstick[i] = new Chopstick();
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        //产生5个哲学家线程
        for(int i=0; i<size; i++){
            exec.execute(new Philosopers(chopstick[i],chopstick[(i+1)%size],i,pauseFactor));

          /*//下面的代码通过防止循环等待而阻止了死锁的产生
           //让前四位哲学家总是先拿左边的筷子，再拿右边的筷子，而让第五位哲学家先拿右边的筷子
           //这样可以打破循环等待的条件，实际上这时候第五位哲学家总是会由于取不到右边的筷子
           //而阻塞(它右边的筷子已经被第一位哲学家取了)，所以也就不会去拿它左边的筷子，从而
           //第四位哲学家总是可以取得两只筷子第一个就餐，从而不会产生循环等待的结果(从输出中可以看到这一点)．
           if(i<(size-1)){
               exec.execute(new Philosopers(chopstick[i],chopstick[(i+1)%size],i,pauseFactor));
           }
           else{
               exec.execute(new Philosopers(chopstick[(i+1)%size],chopstick[i],i,pauseFactor));
           }
           */
        }

        exec.shutdown();
        System.out.println("press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }
}
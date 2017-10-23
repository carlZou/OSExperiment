package me.hifancy.philosopher.ChandyMisra;

public class stick {
    private int owner;
    private boolean using;
    private int num;

    public synchronized int getOwner() {
        return owner;
    }

    public synchronized void setOwner(int owner) {
        this.owner = owner;
    }

    public synchronized void setStuts(boolean status){
       using = status;
    }

    public synchronized boolean getStatus(){
        return this.using;
    }

    public stick(boolean using,int num){
        this.using = using;
        this.num = num;
    }
}

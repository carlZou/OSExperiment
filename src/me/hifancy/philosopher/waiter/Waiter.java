package me.hifancy.philosopher.waiter;

public class Waiter {
    private static boolean[] used = new boolean[5];

    public synchronized boolean ask(int id){
        if (used[id] || used[(id + 1)%5]){
            return false;
        }
        used[id] = true;
        used[(id+1)%5] = true;
        return true;
    }

    public synchronized void put(int id){
        used[id] = false;
        used[(id+1)%5] = false;
    }

    public static boolean monitor(){
        boolean flag = true;
        for (int i = 0;i < 5;i ++){
            if (used[i] == false){
                flag = false;
                break;
            }
        }
        return flag;
    }
}

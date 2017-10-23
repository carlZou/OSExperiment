package me.hifancy.pv;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class prod {
    private static AtomicInteger num = new AtomicInteger(0);
    private static ArrayList<Object> buffer = new ArrayList<>();


    public static void add(){
        buffer.add(new Object());
    }

    public static void use(){
        buffer.remove(0);
    }

    public static int get(){
        return buffer.size();
    }

}

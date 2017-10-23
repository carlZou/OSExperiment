package me.fancy.pv;

public class Test {
    public static void main(String[] args) {
        Producer p1 = new Producer("p1");
        Producer p2 = new Producer("p2");
        Customer c1 = new Customer("c1");
        Customer c2 = new Customer("c2");
        Customer c3 = new Customer("c3");


        p2.start();
        p1.start();
        c1.start();
        c2.start();
        c3.start();
    }
}

package me.hifancy.philosopher.ruler;

public class Test {
    public static void main(String[] args) {
        fork fork = new fork();
        new philosopher("0",fork).start();
        new philosopher("1",fork).start();
        new philosopher("2",fork).start();
        new philosopher("3",fork).start();
        new philosopher("4",fork).start();

    }
}

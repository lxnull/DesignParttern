package com.gof23.单例模式2;

public class Lazy {

    private Lazy() {
        System.out.println(11111);
    }

    private static Lazy LAZY;

    public static Lazy getInstance() {
        if (LAZY == null) {
            System.out.println(22222);
            LAZY = new Lazy();
        }
        System.out.println(33333);
        return LAZY;
    }
}

class AppTest1 {
    public static void main(String[] args) {
        Lazy.getInstance();
    }
}
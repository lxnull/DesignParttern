package com.gof23.单例模式2;

// 饿汉单例模式
/*
类加载时创建对象
 */
public class Hungry {

    private Hungry() {
        System.out.println(11111);
    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        System.out.println(22222);
        return HUNGRY;
    }
}

class AppTest {
    public static void main(String[] args) {
        Hungry.getInstance();
    }
}
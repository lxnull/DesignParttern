package com.gof23.单例模式;

//饿汉式单例
/*
问题：一开始的时候就加载，可能会浪费空间
*/
public class Hungry {

    private Hungry() {}

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getHungry() {
        return HUNGRY;
    }
}

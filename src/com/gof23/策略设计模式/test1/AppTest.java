package com.gof23.策略设计模式.test1;

public class AppTest {

    public static void main(String[] args) {

    }
}

/*
需求：鸭子游戏，创建不同的鸭子
 */

abstract class Duck {
    public void quack() {
        System.out.println("gaga");
    }
    public void swim() {
        System.out.println("swim");
    }
    public abstract void display();
}

class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("野鸭");
    }
}

class RedHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("红头鸭");
    }
}
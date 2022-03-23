package com.gof23.策略设计模式.test3;

public class AppTest {

    public static void main(String[] args) {

    }
}

/*
需求：鸭子游戏，创建不同的鸭子
现在，顾客提出了新的需求，要鸭子能飞
在test2中，使用多态去改变需求是有问题的，继承下来的方法依然重用性很低（不需要的方法也会因为继承作用到子类上），需要程序员做大量判断（不同的鸭子）

现在，我们通过接口去解决2中的问题
 */
interface Flyable {
    public void fly();
}

interface Quackable {
    public void quick();
}

interface Swimable {
    public void swim();
}
abstract class Duck {
    public abstract void display();
}

class MallardDuck extends Duck implements Flyable,Swimable,Quackable {
    @Override
    public void display() {
        System.out.println("野鸭");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void quick() {
        System.out.println("gaga~~");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }
}

class RedHeadDuck extends Duck implements Flyable,Swimable,Quackable {
    @Override
    public void display() {
        System.out.println("红头鸭");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void quick() {
        System.out.println("gaga~~");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }
}

class RubberDuck extends Duck implements Swimable,Quackable {
    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }

    @Override
    public void quick() {
        System.out.println("gaga~~");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }
}

/*
虽然使用功能接口避免了继承类中不需要的方法，但是依然没有解决工作量大的问题
新增每只鸭子时，都需要挨个接口判断，需不需要实现功能接口。
每实现一个功能接口，都需要去重写功能接口中的抽象方法，工作量更大，代码重复性更高。
 */
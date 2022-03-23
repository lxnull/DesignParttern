package com.gof23.面向对象七大设计原则.接口隔离原则.test1;

// 狗不会飞却要实现接口中fly()的方法，违反了接口隔离原则
public class Dog implements AnimalAble {
    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void walk() {

    }
}

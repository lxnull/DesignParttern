package com.gof23.面向对象七大设计原则.接口隔离原则.test2;

// 把三个功能分成独立的三个接口，类只需要实现自己特定的接口，就像每个类都有自己的service接口，而不是集中放在一个接口中
public class Dog implements EatAble,WalkAble {
    @Override
    public void eat() {

    }

    @Override
    public void walk() {

    }
}

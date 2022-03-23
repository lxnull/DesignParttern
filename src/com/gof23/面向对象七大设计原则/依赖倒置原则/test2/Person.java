package com.gof23.面向对象七大设计原则.依赖倒置原则.test2;

// 遵循依赖倒置原则，依赖于接口开发
// 本来上层的person类直接依赖于下层的各个动物类来完成feed()方法
// 更改之后，person依赖于animal，下层的动物类实现了animal接口，只要是实现了该接口的类上层都可以处理，代码不需要改变
public class Person {

    // 实现一个喂猫的方法
    public void feed(Animal animal) {
        animal.eat();
    }
}

interface Animal {
    void eat();
}

class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

class Test {
    public static void main(String[] args) {
        Person person = new Person();
        person.feed(new Cat());
        person.feed(new Dog());
    }
}

// 现在客户要求新增一个喂狗的方法，
// 我们需要新建一个Dog类，直接将dog对象传入feed()方法即可
class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("狗吃肉");
    }
}
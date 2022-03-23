package com.gof23.面向对象七大设计原则.依赖倒置原则.test1;

// 依赖：类B中的方法需要类A作为参数
// 关联：类B需要类A作为其属性存在

/*
* 依赖倒置原则：上层方法应该依赖于抽象，不能依赖于实现，否则下层方法改变上层方法也要随着改变
* */
public class Person {

    // 实现一个喂猫的方法
    public void feed(Cat cat) {
        cat.eat();
    }

    public void feed(Dog dog) {
        dog.eat();
    }
}

class Cat {
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
// 我们需要新建一个Dog类，再在Person类中重载feed方法，这样做违反了依赖倒置原则，下层需要扩展，上层也要跟着改变。
class Dog {
    public void eat() {
        System.out.println("狗吃肉");
    }
}
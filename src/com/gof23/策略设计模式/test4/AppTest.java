package com.gof23.策略设计模式.test4;

public class AppTest {

    public static void main(String[] args) {
        Duck duck = new RubberDuck();
        duck.swim.swim();
        duck.fly.fly();
        // 更换新策略
        duck.setFly(new FlyByKick());
        duck.fly.fly();
    }
}

/*
/   ，但是代码重复实在过多。
最终，我们使用策略设计模式———通过组合对象而非改变对象的方式来解决问题。
我们不能用类去直接实现接口，而是要把接口的实现类组合到我们的鸭子类上。组合使功能的重用性提高。
子类继承父类的属性及set方法，为属性赋不同的值得到不同的类对象。
父类的属性为方法族的父接口。
同时，这种方法能通过set在运行时变换，之前的接口方式和继承方式都要在源码上修改，不能自由变换。
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

class FlyByWing implements Flyable {
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}

class FlyByRocket implements Flyable {

    @Override
    public void fly() {
        System.out.println("背着火箭飞");
    }
}

class FlyByKick implements Flyable {

    @Override
    public void fly() {
        System.out.println("一脚被踢飞");
    }
}

class Gaga implements Quackable {

    @Override
    public void quick() {
        System.out.println("gaga~~");
    }
}

class Zhizhi implements Quackable {

    @Override
    public void quick() {
        System.out.println("zhizhi~~");
    }
}

class SwimByWeb implements Swimable {

    @Override
    public void swim() {
        System.out.println("用脚蹼游泳");
    }
}

class SwimByAlternator implements Swimable {

    @Override
    public void swim() {
        System.out.println("用发电机游泳");
    }
}

abstract class Duck {

    protected Quackable quack;
    protected Swimable swim;
    protected Flyable fly;

    public abstract void display();

    public void setQuack(Quackable quack) {
        this.quack = quack;
    }

    public void setSwim(Swimable swim) {
        this.swim = swim;
    }

    public void setFly(Flyable fly) {
        this.fly = fly;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "quack=" + quack +
                ", swim=" + swim +
                ", fly=" + fly +
                '}';
    }
}

class MallardDuck extends Duck {
    public MallardDuck() {
        this.fly = new FlyByWing();
        this.quack = new Gaga();
        this.swim = new SwimByWeb();
    }

    @Override
    public void display() {
        System.out.println("野鸭");
    }
}

class RedHeadDuck extends Duck {
    public RedHeadDuck() {
        this.fly = new FlyByWing();
        this.quack = new Gaga();
        this.swim = new SwimByWeb();
    }

    @Override
    public void display() {
        System.out.println("红头鸭");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        this.fly = new FlyByRocket();
        this.quack = new Zhizhi();
        this.swim = new SwimByAlternator();
    }

    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }
}
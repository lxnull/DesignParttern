package com.gof23.策略设计模式.test2;

public class AppTest {

    public static void main(String[] args) {

    }
}

/*
需求：鸭子游戏，创建不同的鸭子
现在，顾客提出了新的需求，要鸭子能飞
 */

abstract class Duck {
    public void quack() {
        System.out.println("gaga~~");
    }
    public void swim() {
        System.out.println("swim");
    }
    public void fly() {
        System.out.println("fly");
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

class RubberDuck extends Duck {
    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }
    /*
    因为橡皮鸭继承了Duck类，所以即使橡皮鸭不会飞也继承了父类的fly()方法
    因此，我们要对fly()方法进行重写。
     */
    @Override
    public void fly() {
        System.out.println("橡皮鸭不会飞");
    }
    // 叫声方法同上的问题
    @Override
    public void quack() {
        System.out.println("zhizhi~~");
    }
}
/*
看起来问题好像解决了，但是并没有。
因为变化不断的出现，我们会往程序中添加各种各样不同的鸭子。
在每次增加鸭子时程序员都要判断新的鸭子会不会飞，会不会叫，针对不同的鸭子要进行不同的处理。
这样也很麻烦，只不过从一个噩梦跳入到另一个噩梦中。
 */
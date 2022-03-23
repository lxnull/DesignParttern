package com.gof23.factory2.easyfactory;

public class Test2 {

    public static void main(String[] args) {
        Food1 food = EasyFactory.getFood(2);
        food.eat();
    }
}

// 上层
//================================================================================================
// 底层

/*
简单工厂：
    优点：
    1.把产品从客户端代码中解耦出来。
    2.服务器端如果修改了具体产品类名不影响客户端调用。（依赖倒置，符合面向接口编程原则）
    缺点：
    1.客户端需要死记硬背产品与常量的映射关系，1对应的是什么，2对应的是什么
    2.如果具体产品变多，简单工厂就会显得十分臃肿。
    3.最重要的是简单工厂只对已有产品解耦合。如果想在工厂中添加新的产品必须违反开闭原则。
 */

// 抽象产品
interface Food1 {
    void eat();
}
// 具体产品
class Hamburger2 implements Food1 {

    @Override
    public void eat() {
        System.out.println("吃汉堡");
    }
}

class Lp implements Food1 {

    @Override
    public void eat() {
        System.out.println("吃凉皮");
    }
}
// 简单工厂
class EasyFactory {
    public static Food1 getFood(int i) {

        Food1 food = null;

        switch (i) {
            case 1:
                food = new Hamburger2();
                break;
            case 2:
                food = new Lp();
                break;
        }

        return food;
    }
}
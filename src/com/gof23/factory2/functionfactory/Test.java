package com.gof23.factory2.functionfactory;

public class Test {

    public static void main(String[] args) {
        Food food = new HumBurgerFactory().getfood();
        food.eat();

        Food food1 = new JiaoZiFactory().getfood();
        food1.eat();

        Bussiness.test(new JiaoZiFactory());
    }
}

// 上层
//================================================================================================
// 底层
/*
工厂方法设计模式优点：
    1.仍然具有简单工厂的优点：服务器端修改类名与用户端无关。（默认约定工厂类的类名永不改变）
    2.当用户需要新的产品类时不需要更改源代码，只需要再创建一个该类的工厂即可。

疑问：既然新的产品是客户端的我们自行创建的，为什么不直接去new这个类的对象，而要去新建这个类的工厂？
答：作者在开发功能时不会仅仅开放抽象工厂，抽象产品，以及具体的工厂及产品，还会有配套的一些相应操作或者提前做好的框架，
    例如配套食品及食品工厂有下面的Bussiness类，该类的test()方法中的参数是Factory类的对象，如果我们不传工厂则无法使用底层的功能。

工厂方法设计模式缺点：
    产品过多会使工厂类爆炸。
 */

interface Factory {
    Food getfood();
}

interface Food {
    void eat();
}

class HumBurger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡");
    }
}

class HumBurgerFactory implements Factory {

    @Override
    public Food getfood() {
        return new HumBurger();
    }
}

class JiaoZi implements Food {

    @Override
    public void eat() {
        System.out.println("吃饺子");
    }
}

class JiaoZiFactory implements Factory {

    @Override
    public Food getfood() {
        return new JiaoZi();
    }
}
// 品尝类
class Bussiness {
    public static void test(Factory factory) {
        Food food = factory.getfood();
        System.out.print("一号评委");
        food.eat();

        Food food1 = factory.getfood();
        System.out.print("二号评委");
        food1.eat();
    }
}
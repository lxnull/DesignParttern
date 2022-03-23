package com.gof23.factory2.easyfactory;

public class Test1 {

    public static void main(String[] args) {
        Food f = new Hamburger();
        f.eat();
    }
}

// 上层
//================================================================================================
// 底层

/*
1.这种获得对象的方式相当脆弱，因为底层开发者如果修改了类名（Hamburger1，Hamburger2，Hamburger3），上层客户代码也要跟着改变，这种方式高度耦合。
违反了依赖倒置原则。
2.开发者修改类名用户就不得不通过底层代码知晓开发者修改的位置，违反了迪米特法则。
3.我们希望的是无论下层方法怎么改变都不影响上层代码，下层只负责提供产品给上层，
 */

// 抽象产品
interface Food {
    void eat();
}
// 具体产品
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡");
    }
}

package com.gof23.面向对象七大设计原则.开闭原则.test1;

/*
* 开闭原则：对添加功能开放，对修改源代码关闭
* */

// 需求：需要给车的价格打8折
public class Test {

    public static void main(String[] args) {
        // 修改Car类的源代码，但是修改Car的get()后只能得到其打折之后的价格，不能得到原价
        Car car = new Car("福特", 8888.8, "red");
        System.out.println(car.getPrice());
    }
}

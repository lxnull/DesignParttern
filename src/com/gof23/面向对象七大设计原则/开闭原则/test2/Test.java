package com.gof23.面向对象七大设计原则.开闭原则.test2;

public class Test {

    // 符合开闭原则的设计，为原类创建一个子类，由子类去实现新的需求
    public static void main(String[] args) {
        Car car = new DiscountCar("福特", 8888.8, "red");
        System.out.println(car.getPrice());
    }
}

package com.gof23.面向对象七大设计原则.开闭原则.test2;

public class DiscountCar extends Car {

    public DiscountCar() {
        super();
    }

    public DiscountCar(String brand, double price, String color) {
        super(brand, price, color);
    }

    @Override
    public double getPrice() {
        return super.getPrice() * 0.8;
    }
}

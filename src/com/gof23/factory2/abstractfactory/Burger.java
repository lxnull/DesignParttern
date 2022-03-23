package com.gof23.factory2.abstractfactory;

public class Burger implements Food {
    @Override
    public void eat() {
        System.out.println("吃汉堡");
    }
}

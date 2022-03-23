package com.gof23.factory.method;

//方法工厂模式
public class Customer {

    public static void main(String[] args) {

        new WuLingFactory().getCar().name();
        new TeslaFactory().getCar().name();
    }
}

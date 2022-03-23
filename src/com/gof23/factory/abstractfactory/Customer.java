package com.gof23.factory.abstractfactory;

//抽象工厂，能增加产品族，增加产品等级会改变源码
public class Customer {

    public static void main(String[] args) {
        new HuaweiFactory().getPhone().callUp();
        new HuaweiFactory().getRouter().start();
        new XiaomiFactory().getPhone().callUp();
        new XiaomiFactory().getRouter().start();
    }
}

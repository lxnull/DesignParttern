package com.gof23.factory.simple;

public class Customer {

    public static void main(String[] args) {

        Car wl = CarFactory.getCar("wl");
        wl.name();
        Car tesla = CarFactory.getCar("tesla");
        tesla.name();
        Car wl2 = CarFactory.wuling();
        wl2.name();
        Car tesla2 = CarFactory.tesla();
        tesla2.name();
    }
}

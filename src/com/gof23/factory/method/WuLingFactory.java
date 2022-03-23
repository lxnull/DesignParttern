package com.gof23.factory.method;

public class WuLingFactory implements Factory {

    @Override
    public Car getCar() {
        return new WuLing();
    }
}

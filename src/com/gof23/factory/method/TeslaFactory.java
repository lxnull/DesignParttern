package com.gof23.factory.method;

public class TeslaFactory implements Factory {
    @Override
    public Car getCar() {
        return new Tesla();
    }
}

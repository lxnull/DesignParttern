package com.gof23.factory2.abstractfactory;

public class Cola implements Drinks {
    @Override
    public void drink() {
        System.out.println("喝可乐");
    }
}

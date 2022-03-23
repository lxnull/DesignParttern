package com.gof23.factory2.abstractfactory;

public class KFCFactory implements Factory {
    @Override
    public Burger getFood() {
        return new Burger();
    }

    @Override
    public Cola getDrink() {
        return new Cola();
    }
}

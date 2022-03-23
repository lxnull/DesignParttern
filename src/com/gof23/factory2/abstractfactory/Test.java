package com.gof23.factory2.abstractfactory;

public class Test {

    public static void main(String[] args) {
        Factory factory = new KFCFactory();
        Drinks drink = factory.getDrink();
        drink.drink();
        Food food = factory.getFood();
        food.eat();
    }
}

package com.gof23.装饰器设计模式.test1;

public class AppTest {

    public static void main(String[] args) {
        HouseBlend houseBlend = new HouseBlend();
        System.out.println(houseBlend.getDescription()+":"+houseBlend.cost()+"元");
    }
}

//=========================================================================================

/*
业务场景：咖啡馆买咖啡，一开始，只有四款咖啡，Decaf,Espresso,DrakRoast,HouseBlend
因为所有种类的咖啡都是饮品，所以我们安排一个Beverage（饮品）父类，把他们共同的属性提取到父类上。
 */
// 抽象的饮品父类
abstract class Beverage {
    // 描述
    private String description;

    public Beverage(String description) {
        this.description = description;
    }

    // 花费
    public abstract int cost();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

class Decaf extends Beverage {

    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public int cost() {
        return 8;
    }
}

class Espresso extends Beverage {

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public int cost() {
        return 10;
    }
}

class DrakRoast extends Beverage {

    public DrakRoast() {
        super("焦炒咖啡");
    }

    @Override
    public int cost() {
        return 10;
    }
}

class HouseBlend extends Beverage {

    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public int cost() {
        return 12;
    }
}
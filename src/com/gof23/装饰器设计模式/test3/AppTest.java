package com.gof23.装饰器设计模式.test3;

public class AppTest {

    public static void main(String[] args) {
        HouseBlend houseBlend = new HouseBlend();
        houseBlend.setA(true);
        houseBlend.setB(true);
        System.out.println(houseBlend.getDescription()+":"+houseBlend.cost()+"元");
    }
}
/*
优点：
1.类没有爆炸，没有出现各种各样奇奇怪怪的类
2.添加新的饮料不会受到影响。符合开闭原则。
缺点：
1.对于添加配料而言违反了开闭原则，必须为父类添加新的字段，修改cost()方法才能实现新配料的添加。
 */

//=========================================================================================

/*
新增需求：添加各种的饮料配料

跟据test2包中的问题，我们何必为每一种本类饮品的变种去创建那么多新的类呢，这样做太笨了！！！！！！！
我们可以在父类Beverage中添加各种配料的属性，把他们设置为Boolean类型，为true时相应的信息拼接到返回的产品中。
 */
// 抽象的饮品父类
abstract class Beverage {
    // 描述
    private String description;
    // 配料
    private boolean a,b,c,d;

    public Beverage(String description) {
        this.description = description;
    }

    // 花费
    public int cost() {
        int sum = 0;
        if (a) {
            sum += 1;
        }
        if (b) {
            sum += 1;
        }
        if (c) {
            sum += 2;
        }
        if (d) {
            sum += 3;
        }

        return sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setA(boolean a) {
        this.a = a;
        if (a) {
            description = description + "a";
        }
    }

    public void setB(boolean b) {
        this.b = b;
        if (b) {
            description = description + "b";
        }
    }

    public void setC(boolean c) {
        this.c = c;
        if (c) {
            description = description + "c";
        }
    }

    public void setD(boolean d) {
        this.d = d;
        if (d) {
            description = description + "d";
        }
    }
}

class Decaf extends Beverage {

    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public int cost() {
        return 8 + super.cost();
    }
}

class Espresso extends Beverage {

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public int cost() {
        return 10 + super.cost();
    }
}

class DrakRoast extends Beverage {

    public DrakRoast() {
        super("焦炒咖啡");
    }

    @Override
    public int cost() {
        return 10 + super.cost();
    }
}

class HouseBlend extends Beverage {

    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public int cost() {
        return 12 + super.cost();
    }
}
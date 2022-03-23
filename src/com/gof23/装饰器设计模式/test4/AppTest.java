package com.gof23.装饰器设计模式.test4;

public class AppTest {

    public static void main(String[] args) {
        Beverage houseBlend = new HouseBlend();
        Beverage milk = new Milk(houseBlend);
        Beverage suger = new Suger(milk);
        System.out.println(suger.getDescription()+":"+suger.cost()+"元");
    }
}
/*
最终解决方案：
适配器设计模式：这个模式下的所有类都依赖于同一个父类，这些子类分为两类，一类为正常继承父类功能的子类，另一类为其余子类的包装类，
同时包装类与父类组合，父类作为包装类中的一个属性。这样做使我们可以把其余子类无限包装在包装类中，因为包装类同样继承自父类，所以包装类（经过包装的其余子类）
也可以再次被包装。运行时一层一层的调用被包装的类的方法。

优点：加任何类（含有‘is a’关系的类，包装类的配件子类）都不需要更改源代码，遵循迪米特法则。
缺点：虽然已经尽可能的控制了类的数量，但类还是太多。
 */

//=========================================================================================

/*
新增需求：添加各种的饮料配料

上一个包中的缺点：
对于添加配料而言违反了开闭原则，必须为父类添加新的字段，修改cost()方法才能实现新配料的添加。
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

// 调料类继承饮品类
abstract class Condiment extends Beverage {
    // 让调料类关联饮料类
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

// 调料1
class Milk extends Condiment {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "牛奶";
    }
}

// 调料2
class Soy extends Condiment {

    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "豆浆";
    }
}

// 调料2
class Suger extends Condiment {

    public Suger(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 1;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "糖";
    }
}
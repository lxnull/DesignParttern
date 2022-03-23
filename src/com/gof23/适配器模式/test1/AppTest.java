package com.gof23.适配器模式.test1;
/*
适配器：
    一个类的接口转换成客户希望的另一个接口，适配器模式可以让那些接口不兼容的类在一起工作。
    跟据已有的接口，生成想要的结果。
 */
public class AppTest {

    public static void main(String[] args) {
        new Calc().add(1,2);

        int add = new CalcAdapter(new Calc()).add(1, 2, 3);
        System.out.println(add);

        int add1 = new CalcAdapter2().add(1, 2, 3);
        System.out.println(add1);
    }
}

/*
我们为客户提供一个算数的类，这个类的add()方法，可以传两个int类型的参数。

现在，客户有了新的需求，他需要add()方法能计算三个整数的和。
这种需求虽然用户可以通过调用add自己完成，但不能让客户修改我们的代码，如此下去肯定会出别的问题。
 */
class Calc {
    public int add(int a, int b) {
        return a + b;
    }
}

// 通过组合实现需求
class CalcAdapter {
    private Calc c;

    public CalcAdapter(Calc c) {
        this.c = c;
    }

    public int add(int x, int y, int z) {
        return c.add(x,c.add(y,z));
    }
}

// 通过继承实现需求
class CalcAdapter2 extends Calc {

    public int add(int a, int b, int c) {
        return add(a, add(b, c));
    }
}
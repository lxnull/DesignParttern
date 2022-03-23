package com.gof23.模板设计模式;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        new ApplaictionTest().timeTest();
        new ApplaictionTest1().timeTest();
    }
}

/*
需求：比较LinkList和ArrayList的区别
 */

abstract class Appliaction {
    public void timeTest() {
        System.out.println("开始");
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("结束");

        System.out.println(end - start);
    }

    public abstract void code();
}

class ApplaictionTest extends Appliaction {

    @Override
    public void code() {
        List list = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
}

class ApplaictionTest1 extends Appliaction {

    @Override
    public void code() {
        List list = new ArrayList();

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
}

/*
模板设计模式：
一种基于抽象类的，核心是封装逻辑步骤的设计模式。
比如我们要去做饮料喝，基本的逻辑步骤是：将水煮沸--》冲泡饮料--》倒入杯中
煮沸水，导入杯中这两个步骤是永远不会改变的
冲泡饮料是可变的，我们冲泡的饮料可以是茶包，可以是咖啡，也可以是奶茶
如此，我们便有了一个整体逻辑几乎不变，而细节部分需要调整的逻辑，模板设计模式就是用来解决这一类的问题。
我们将冲泡饮料这一步骤定义为一个抽像方法，由具体的子类去重写它到底要冲泡什么饮料。
将整个这一过程封装为一个抽象类，因为我们整体的逻辑是完整的，是一个不需要抽象的普通方法，代表变数的方法是抽象的，所以要使用抽象类，不能使用接口，
 */
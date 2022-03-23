package com.gof23.动态代理.test1;

/*
需求：为该类计算数据的方法添加日志功能。
设想方法：多态，日志类，策略设计模式，因为每次有新方法都要写一次日志，繁杂pass。
 */
public class AppTest {
    public static void main(String[] args) {

    }
}

interface Calc {
    public int add(int a, int b);
    public int sub(int a, int b);
    public int mul(int a, int b);
    public int div(int a, int b);
}

class CalcImpl implements Calc {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
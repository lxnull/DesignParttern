package com.gof23.面向对象七大设计原则.homework;

public class Test2 {

    public static void main(String[] args) {
        Test2.f();
    }

    static Test2 t = new Test2();

    static {
        System.out.println(1);
    }

    {
        System.out.println(2);
    }

    public Test2() {
        System.out.println(3);
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void f() {
        System.out.println(4);
    }

    {
        System.out.println(5);
    }

    int a = 110;
    static int b = 112;
}

package com.gof23.面向对象七大设计原则.homework;

public class Test3 {

    static boolean flag;
    static boolean flag1;


    public static void main(String[] args) {

        Object o = new Object();

        Thread thread = new Thread(new MyThread1(o));
        Thread thread1 = new Thread(new MyThread2(o));

        thread.start();
        thread1.start();
    }
}

class MyThread1 implements Runnable {

    Object o;

    public MyThread1(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        synchronized (o) {
            for (int i = 1; i <= 52; i++) {
                System.out.println(Thread.currentThread().getName() + i);
                if (i % 2 == 0) {
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class MyThread2 implements Runnable {

    Object o;

    public MyThread2(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        synchronized (o) {
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.println(Thread.currentThread().getName() + (char) i);
                try {
                    o.notifyAll();
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
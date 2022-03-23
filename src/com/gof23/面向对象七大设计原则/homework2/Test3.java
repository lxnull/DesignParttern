package com.gof23.面向对象七大设计原则.homework2;

public class Test3 {

    public static void main(String[] args) {
        Runnable r = new T();
        Thread t = new Thread(r);
        t.start();

        try {
            Thread.sleep(2000);
            while (true) {
                synchronized (r) {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.println(Thread.currentThread().getName()+"---->"+i);
                        }
                        r.notifyAll();
                        r.wait();
                        if (T.count >= 50) {
                            r.notifyAll();
                            System.out.println("main:" + Thread.currentThread().getName() + T.count);
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T implements Runnable {

    public static int count = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName()+"---->"+i);
                    }
                    this.notifyAll();
                    this.wait();
                    if (count >= 50) {
                        System.out.println("Thread-0:" + Thread.currentThread().getName() + T.count);
//                        this.notifyAll();
                        break;
                    }
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

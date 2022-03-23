package com.gof23.动态代理.test9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Apptest {

    public static void main(String[] args) {
        Runnable proxy = (Runnable) MyProxy.getProxy(new Thread1());
        Thread thread = new Thread(proxy);
        thread.start();
        System.out.println(Thread.currentThread().getName());

        Thread1 thread11 = new Thread1();
        Thread thread1 = new Thread(thread11);
        thread1.start();
    }
}

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyProxy {

    public static Object getProxy(Object target) {
        ClassLoader classLoader = MyProxy.class.getClassLoader();
        Class[] classes = target.getClass().getInterfaces();
        MyHandler myHandler = new MyHandler(target);
        return Proxy.newProxyInstance(classLoader, classes, myHandler);
    }
}

class MyHandler implements InvocationHandler {

    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
package com.gof23.动态代理.test3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        Calc myProxy = (Calc) MyProxy.getMyProxy(new CalcImpl());
        myProxy.add(1,2);
    }
}

/*
用户
========================================================================================================================
底层
 */

class MyProxy {
    public static Object getMyProxy(Object target) {
        ClassLoader classLoader = MyProxy.class.getClassLoader();// 类加载器

        Class[] interfaces = target.getClass().getInterfaces();// 字节码

        return Proxy.newProxyInstance(classLoader, interfaces, new MyHandler(target));
    }
}

class MyHandler implements InvocationHandler {

    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入“" + method.getName() + "(" + Arrays.toString(args) + ")" + "”方法");

        method.invoke(target, args);

        System.out.println("离开“" + method.getName() + "(" + Arrays.toString(args) + ")" + "”方法");
        return 0;
    }
}

/*
现在的问题：
现在我们的代理对象只能为方法添加日志，无法比较灵活的扩展其它功能。
比如缓存，权限控制，延迟加载。。。。。。
 */

/*
========================================================================================================================
以下的计算类是由我们发现的，作者并不是我们。
我们要为其增加功能，并且不能改变它的源代码。
========================================================================================================================
 */

interface Calc {
    public int add(int a, int b);
    public int sub(int a, int b);
    public int mul(int a, int b);
    public int div(int a, int b);
}

class CalcImpl implements Calc {

    @Override
    public int add(int a, int b) {
        int c = a + b;
        System.out.println(c);
        return c;
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
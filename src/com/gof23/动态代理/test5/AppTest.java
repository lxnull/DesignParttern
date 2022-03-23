package com.gof23.动态代理.test5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        Calc calc = new CalcImpl();
        Calc calc1 = (Calc) MyProxy.getMyProxy(calc,new A());
        Calc calc2 = (Calc) MyProxy.getMyProxy(calc1, new B());
        calc2.sub(1,2);
    }
}

class A implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if (method.getName().equals("add")) {
            System.out.println("进入 " + method.getName() + " 参数是 " + Arrays.toString(args));
        }
    }

    @Override
    public void after(Method method, Object res) {
        if (method.getName().equals("add")) {
            System.out.println("离开 " + method.getName() + " 返回值 " + res);
        }
    }
}

class B implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if (method.getName().equals("sub")) {
            System.out.println("begin " + method.getName() + " parameters " + Arrays.toString(args));
        }
    }

    @Override
    public void after(Method method, Object res) {
        if (method.getName().equals("sub")) {
            System.out.println("over " + method.getName() + " results " + res);
        }
    }
}
/*
用户
========================================================================================================================
底层
 */

/*
现在的问题：
在一次操作中提供多种策略，比如说打印中文或者打印英文
 */
interface Interceptor {
    public void before(Method method, Object[] args);

    public void after(Method method, Object res);
}

class MyProxy {
    public static Object getMyProxy(Object target, Interceptor interceptor) {
        ClassLoader classLoader = MyProxy.class.getClassLoader();// 类加载器

        Class[] interfaces = target.getClass().getInterfaces();// 字节码

        return Proxy.newProxyInstance(classLoader, interfaces, new MyHandler(target,interceptor));
    }
}

class MyHandler implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public MyHandler(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        interceptor.before(method,args);
        Object res = method.invoke(target, args);
        interceptor.after(method,res);
        return 0;
    }
}


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
        int c = a - b;
        System.out.println(c);
        return c;
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
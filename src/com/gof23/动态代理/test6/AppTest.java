package com.gof23.动态代理.test6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        Calc calc = new CalcImpl();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new A());
        interceptors.add(new B());
        interceptors.add(new C());
        // 多层嵌套代理调用参数为集合的方法
        Calc proxy = (Calc) MyProxy.getMyProxyByList(calc,interceptors);
        proxy.add(1,2);
        System.out.println(A.class.getName());
    }
}

class A implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Override
    public void after(Method method, Object res) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
}

class B implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
    }

    @Override
    public void after(Method method, Object res) {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
    }
}

class C implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
    }

    @Override
    public void after(Method method, Object res) {
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
    }
}
/*
用户
========================================================================================================================
底层
 */

/*
现在的问题：
实现多层嵌套代理
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

    public static Object getMyProxyByList(Object target, List<Interceptor> interceptors) {
        for (Interceptor interceptor : interceptors) {
            target = MyProxy.getMyProxy(target,interceptor);
        }

        return target;
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
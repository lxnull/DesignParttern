package com.gof23.动态代理.test4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        Calc myProxy = (Calc) MyProxy.getMyProxy(new CalcImpl(),new A());
        myProxy.add(1,2);
    }
}

class A implements Interceptor {

//    @Override
//    public void before() {
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//    }

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("进入" + method.getName() + "参数是" + Arrays.toString(args));
    }

//    @Override
//    public void after() {
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//    }

    @Override
    public void after(Method method, Object res) {
        System.out.println("离开" + method.getName() + "返回值" + res);
    }
}
/*
用户
========================================================================================================================
底层
 */

/*
现在的问题：
现在我们的代理对象只能为方法添加日志，无法比较灵活的扩展其它功能。
比如缓存，权限控制，延迟加载。。。。。。

通过创建拦截器接口来解决这个问题。Interceptor
将Interceptor作为MyHandler的一个属性组合在其中，
在代理类方法中调用Interceptor的before和after方法。
由用户传Interceptor的实现类，由用户决定环绕策略。

但是这种方法只能提供一种策略，我们在不同时候需要不同的拦截器策略，在test5包中解决这个问题。
 */
interface Interceptor {
//    public void before();
    // 进入时打印方法名和参数列表
    public void before(Method method, Object[] args);
//    public void after();
    // 离开时打印方法名和返回值
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
//        System.out.println("进入“" + method.getName() + "(" + Arrays.toString(args) + ")" + "”方法");
        // 前置方法
        interceptor.before(method,args);
        method.invoke(target, args);
        // 后置方法
        interceptor.after(method,null);
//        System.out.println("离开“" + method.getName() + "(" + Arrays.toString(args) + ")" + "”方法");
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
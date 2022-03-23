package com.gof23.动态代理.test7;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class AppTest {
    public static void main(String[] args) throws IOException {
        try {
            Calc c = (Calc) MyProxy.getMyProxyByList(new CalcImpl(), "G:\\JavaSpace\\Java进阶\\设计模式\\proxy.properties");
            c.add(1,2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
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
跟据配置文件实现多层嵌套代理
配置文件：proxy.properties
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

    public static Object getMyProxyByList(Object target, String path) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 拦截器集合
        List<Interceptor> interceptors = new ArrayList<>();
        // 加载资源（ResourceBundle用来读取.properties文件）
        ResourceBundle bundle = ResourceBundle.getBundle("proxy");
        String str = bundle.getString("interceptor");
        String strCN = new  String(str.getBytes( "ISO8859-1" ), "GB2312" );

        // System.out.println(strCN);

        // 处理资源
        String[] strings = strCN.split(",");
        for (String name : strings) {
            interceptors.add((Interceptor) Class.forName(name).newInstance());
        }
        // 多层嵌套拦截器
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
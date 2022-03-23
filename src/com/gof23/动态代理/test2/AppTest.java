package com.gof23.动态代理.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        // 1.创建代理对象所需的第一个参数————ClassLoader
        /*
        动态代理是一个动态创建对象，然后进行操作的过程，想要获得对象，第一步操作就是获取类加载器。
        动态代理加载字节码文件与jvm加载字节码文件的不同点就是需要我们手动填入类加载器。
        我们直接使用当前类的类加载器进行填入。
         */
        ClassLoader classLoader = AppTest.class.getClassLoader();
        // 2.创建代理对象所需的第二个参数————字节码数组
        /*
        我们都知道创建对象需要类加载器加载字节码文件，
        现在我们现在已经获得了类加载器，填入字节码数组之后，通过动态代理api动态生成的字节码内容，就可以创建被代理类的字节码了。
        在本例中，通过字节码数组，就会动态生成一个实现了Calc接口的字节码。
         */
        Class[] interfaces = new Class[]{Calc.class};
        // 3.创建代理对象所需的第三个参数————InvocationHandler（调用处理器）
        /*
        现在，我们已经通过ClassLoader和Class数组获得了实现Calc接口的类的字节码文件。
        现在的问题是：实现了一个接口，就必然要实现这个接口的抽象方法，Calc中的抽象方法必须要被实现。
        而抽象方法实现后的内容是由这第三个参数确定的。
        因为InvocationHandler是一个接口，所以我们需要创建其实现类MyHandler
         */
        MyHandler myHandler = new MyHandler(new CalcImpl());
        // 创建一个动态代理对象
        Calc calc = (Calc) Proxy.newProxyInstance(classLoader, interfaces, myHandler);
        // 4.调用代理类中的方法
        /*
        代理对象调用的方法统统会转到调用处理器中（InvocationHandler）,不会进入真实的方法。
        我们要在调用处理器中通过反射，去调用真实对象的方法
         */
        calc.add(1,2);
        calc.div(1,2);
    }
}

class MyHandler implements InvocationHandler {

    // 5.与真实对象组合
    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("geigeigei~~");

        System.out.println(method.getName() + "(" + Arrays.toString(args) + ")");
        int invoke = (int) method.invoke(target, args);
        System.out.println(invoke);

        System.out.println("heiheihei~~");
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

/*
代理模式完全结束，下个test3中简化代码
 */
package com.gof23.单例模式;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Lazy {

    //防守2.0：创建标志位
    private static boolean flag = false;

    private Lazy() {
        //System.out.println(Thread.currentThread().getName());

        //防守1.0：构造方法上锁
        synchronized (Lazy.class) {

            if (flag == false) {
                flag = true;
            } else{
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
    }

    //volatile：避免指令重排
    private volatile static Lazy lazy;

    public static Lazy getLazy() {

        if (lazy == null) {
            synchronized (Lazy.class) {
                lazy = new Lazy();//非原子性操作
                /*
                 * 1、分配内存空间
                 * 2、执行构造方法，初始化对象
                 * 3、对象指向空间
                 * 问题：可能会导致指令重排，如果在CPU中按照132的顺序执行，可能会指向一个空的空间。
                 */
            }
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                Lazy.getLazy();
//            }).start();
//        }

        //攻击1.0：反射可以破坏单例
        //攻击2.0：不使 getLazy(); 创建Lazy中的对象lazy，构造方法锁失效
        //Lazy lazy = Lazy.getLazy();

        //攻击3.0：破解标志位
        Field flag = Lazy.class.getDeclaredField("flag");
        flag.setAccessible(true);

        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        Lazy lazy1 = declaredConstructor.newInstance();
        //攻击3.0：破解标志位
        flag.set(flag, false);

        Lazy lazy2 = declaredConstructor.newInstance();

        System.out.println(lazy2.equals(lazy1));
        System.out.println(lazy2 == lazy1);

        System.out.println(lazy1.hashCode());
        System.out.println(lazy2.hashCode());
    }


}

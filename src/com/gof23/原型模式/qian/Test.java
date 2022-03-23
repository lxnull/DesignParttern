package com.gof23.原型模式.qian;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    private static String str = new String("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        // 原型对象
        WeekReport weekReport = WeekReport.getWeekReport();
        System.out.println(getTimeString(weekReport.getCreateTime(), str));
        // 克隆对象
        WeekReport clone = (WeekReport) weekReport.clone();
        System.out.println(getTimeString(clone.getCreateTime(), str));

        Thread.sleep(3000);

        // 克隆出的对象修改属性
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        clone.setCreateTime(calendar);

        System.out.println(getTimeString(weekReport.getCreateTime(),str));
        System.out.println(getTimeString(clone.getCreateTime(),str));

        System.out.println("=========================================================================");

        // 克隆出的对象修改属性2（构造数据类型）
        clone.getEmp().setName("ls");

        System.out.println(weekReport.getEmp().getName());
        System.out.println(clone.getEmp().getName());
        /*
        问题：为什么改变clone出来的对象的员工属性会使原型对象的属性改变。
        答：因为使用克隆方法克隆出的对象是浅克隆，直接复制一份原型对象的二进制代码来生成新的对象，
           在以上的例子中，原型对象的Emp属性为一个名为zs的对象，它存储的是指向堆中zs对象的地址，
           因此我们的clone是把这串地址码复制了下来，所以新对象的Emp属性指向的依然是zs这个对象。
         */
    }

    public static String getTimeString(Calendar calendar, String str) {
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(time);
    }
}
/*
原型模式1：使用克隆方法
   *问题1：clone方法会不会引起构造器调用？clone方法是如何实现克隆对象的效果？
    答：不会。clone方法是直接赋值内存中的二进制。效率更高。（native）
   *问题2：clone方法克隆出的对象是否和原型对象内存地址一致？
    答：不一致，最终得到的是两个不同空间的对象。

   *在克隆出新对象后，我们只需要修改我们要修改的，只设置变化的部分。
 */
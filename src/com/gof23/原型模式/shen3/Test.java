package com.gof23.原型模式.shen3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    private static String str = new String("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        WeekReport wr = WeekReport.getWeekReport();
        WeekReport clone = wr.clone();

        System.out.println(clone.equals(wr));

        clone.getEmp().setName("李四");

        System.out.println(wr.getEmp().getName());
        System.out.println(clone.getEmp().getName());
    }

    public static String getTimeString(Calendar calendar, String str) {
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(time);
    }
}
/*
最终版本的深克隆：把通过序列化对象得到的流，通过在内存中开辟空间而存储在内存中，
与存储在硬盘相比，解决了代码与操作系统耦合的问题。
 */
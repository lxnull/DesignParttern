package com.gof23.原型模式.shen2;

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
通过将对象序列化的形式避免直接处理对象之间相互嵌套的复杂结构，
但这种方式依然存在一个问题，就是于windows耦合程度太高，Linux中不存在存储盘符的操作，这抹去了Java语言跨平台的优点。
 */
package com.gof23.原型模式.shen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    private static String str = new String("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        // 原型
        WeekReport wr = WeekReport.getWeekReport();
        // 克隆出的对象
        WeekReport clone = wr.clone();
        // 修改克隆对象的属性值
        clone.getEmp().setName("李四");
        // 原型未发生改变
        System.out.println(wr.getEmp().getName());
        System.out.println(clone.getEmp().getName());
        /*
        通过对clone方法的重写，把原型对象的Emp属性进行clone一次之后再赋给被clone出的对象，
        实现了深clone，Emp属性指向了堆中不同的地址。原型与克隆体之间再无影响。
         */
    }

    public static String getTimeString(Calendar calendar, String str) {
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(time);
    }
}
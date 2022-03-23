package com.gof23.factory.simple;

public class CarFactory {

    //方法一：新增会改变本来逻辑代码
    public static Car getCar(String str){

        if (str.equals("wl")) {
            return new WuLing();
        } else if (str.equals("tesla")) {
            return new Tesla();
        }
        return null;
    }

    //方法二：相比而言不用改变逻辑代码，但依然要修改源代码
    public static Car wuling() {
        return new WuLing();
    }

    public static Car tesla() {
        return new Tesla();
    }
}

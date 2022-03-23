package com.gof23.建造者模式.test1;

public class TestBuilder1 {

    public static void main(String[] args) {
        Computer c = new Computer();
        c.setCpu("i5-7300HQ");
        c.setGpu("gtx1050ti");
        c.setMemery("8g");
        c.setHd("1T机械+256G固态");
        System.out.println(c.toString());
    }
}
/*
这样做的缺点是：
1.客户端程序员在实例化好产品对象之后，必须为对象的每一个属性赋值，这种操作对于客户端程序员太麻烦。
2.违反了迪米特法则。

这相当于你去配电脑，商家把零件全给你，让你自己组装。

建造者模式与工厂模式的区别：
1.工厂模式：直接实例化出一个类的对象即可，注重的是得到对象。
2.建造者模式：是在实例化出类对象后，还要给类的属性赋值，注重的是给对象赋值的过程。
 */

//====================================================================================

/*
需求：定义一个电脑类，实例化电脑对象，并且给电脑对象的属性赋值。
 */

class Computer {
    private String cpu;
    private String gpu;
    private String memery;
    private String hd;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMemery() {
        return memery;
    }

    public void setMemery(String memery) {
        this.memery = memery;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", memery='" + memery + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }
}
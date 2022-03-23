package com.gof23.建造者模式.test2;

public class TestBuilder1 {

    public static void main(String[] args) {
        Computer computer = new ComputerBuilder().getComputer();
        System.out.println(computer.toString());
    }
}

//====================================================================================

/*
需求：定义一个电脑类，实例化电脑对象，并且给电脑对象的属性赋值。
 */

/*
之前的问题是：
1.客户端程序员在实例化好产品对象之后，必须为对象的每一个属性赋值，这种操作对于客户端程序员太麻烦。
2.违反了迪米特法则。
这相当于你去配电脑，商家把零件全给你，让你自己组装。

优点：
1.客户端程序员需要一个产品时直接向建造者要就可，封装了创建对象的复杂过程、
缺点：
1.封装的太死，不能跟据用户的需求灵活改变配置，得到的全部是相同的产品。
 */

// 为此我们需要一个建造者类，为我们提供完整的电脑产品，省去用户自己组装的过程。
class ComputerBuilder {
    private Computer c = new Computer();

    public Computer getComputer() {
        c.setCpu("i5-7300HQ");
        c.setGpu("gtx1050ti");
        c.setMemery("8g");
        c.setHd("1T机械+256G固态");

        return c;
    }
}

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
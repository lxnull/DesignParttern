package com.gof23.建造者模式.test3;

public class TestBuilder1 {

    public static void main(String[] args) {
        Computer computer = new ComputerABuilder().getComputer();
        System.out.println(computer.toString());
    }
}

//====================================================================================

/*
需求：定义一个电脑类，实例化电脑对象，并且给电脑对象的属性赋值。
 */

/*
之前的问题是：
封装的太死，不能跟据用户的需求灵活改变配置，得到的全部是相同的产品。

问题：
建造者类代码大量重复。
制造产品的过程不稳定。在用户扩展自己的建造者类时可能会忘记给某一属性赋值。
 */

// 为此我们需要满足不同需求的多个建造者类
class ComputerABuilder {
    private Computer c = new Computer();

    public Computer getComputer() {
        c.setCpu("a");
        c.setGpu("a");
        c.setMemery("a");
        c.setHd("a");

        return c;
    }
}

class ComputerBBuilder {
    private Computer c = new Computer();

    public Computer getComputer() {
        c.setCpu("b");
        c.setGpu("b");
        c.setMemery("b");
        c.setHd("b");

        return c;
    }
}

class ComputerCBuilder {
    private Computer c = new Computer();

    public Computer getComputer() {
        c.setCpu("c");
        c.setGpu("c");
        c.setMemery("c");
        c.setHd("c");

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
package com.gof23.建造者模式.test5;

public class TestBuilder1 {

    public static void main(String[] args) {
        Computer computer = new Director().getComputer(new ComputerABuilder());
        System.out.println(computer.toString());

        Computer computer1 = new Director().getComputer(new ComputerBBuilder());
        System.out.println(computer1.toString());

        Computer computer2 = new Director().getComputer(new ComputerCBuilder());
        System.out.println(computer2.toString());
    }
}

//====================================================================================

/*
需求：定义一个电脑类，实例化电脑对象，并且给电脑对象的属性赋值。
 */

/*

 */

// 最终简化流程
class Director {

    public Computer getComputer(Builder builder) {
        builder.setCpu();
        builder.setGpu();
        builder.setMemery();
        builder.setHd();

        return builder.getComputer();
    }
}

// 为此我们需要规范化建造者的建造流程，创建一个规范化流程的接口。
interface Builder {
    public void setCpu();
    public void setGpu();
    public void setMemery();
    public void setHd();

    public Computer getComputer();
}

class ComputerABuilder implements Builder {
    private Computer c = new Computer();

    @Override
    public void setCpu() {
        c.setCpu("a");
    }

    @Override
    public void setGpu() {
        c.setGpu("a");
    }

    @Override
    public void setMemery() {
        c.setMemery("a");
    }

    @Override
    public void setHd() {
        c.setHd("a");
    }

    public Computer getComputer() {
        return c;
    }
}

class ComputerBBuilder implements Builder {
    private Computer c = new Computer();

    @Override
    public void setCpu() {
        c.setCpu("b");
    }

    @Override
    public void setGpu() {
        c.setGpu("b");
    }

    @Override
    public void setMemery() {
        c.setMemery("b");
    }

    @Override
    public void setHd() {
        c.setHd("b");
    }

    public Computer getComputer() {
        return c;
    }
}

class ComputerCBuilder implements Builder {
    private Computer c = new Computer();

    @Override
    public void setCpu() {
        c.setCpu("c");
    }

    @Override
    public void setGpu() {
        c.setGpu("c");
    }

    @Override
    public void setMemery() {
        c.setMemery("c");
    }

    @Override
    public void setHd() {
        c.setHd("c");
    }

    public Computer getComputer() {
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
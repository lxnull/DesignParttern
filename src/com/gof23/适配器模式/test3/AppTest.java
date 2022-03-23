package com.gof23.适配器模式.test3;

public class AppTest {

    public static void main(String[] args) {
        Apply.process("Hello World",new Downcase());
    }
}

class Processor {
    public String name() {
        return this.getClass().getSimpleName();
    }

    Object process(Object input) {
        return input;
    }
}

class Upcase extends Processor {
    @Override
    public String name() {
        return super.name();
    }
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    String process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class Downcase extends Processor {
    @Override
    public String name() {
        return super.name();
    }
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    String process(Object input) {
        return ((String)input).toLowerCase();
    }
}

class Splitter extends Processor {
    @Override
    public String name() {
        return super.name();
    }
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    String[] process(Object input) {
        return ((String)input).split(" ");
    }
}

/*
Apply.process()可以接受Processor及其任何子类，并将Processor及其子类对字符串的处理实现在Object对象上，
解决了test2中代码重复的问题。
 */
class Apply {
    public static void process(Object s,Processor processor) {
        System.out.println(processor.name());
        System.out.println(processor.process(s));
    }
}
package com.gof23.适配器模式.test2;

public class AppTest {

    public static void main(String[] args) {
        String str = "hello world";
        Processor p = new Upcase();
        System.out.println(p.name());
        System.out.println((String) p.process(str));

        String str2 = new Downcase().process(str);
        System.out.println(str2);

        String[] strings = new Splitter().process(str);
        for (String s : strings) {
            System.out.println(s);
        }

        Splitter splitter = new Splitter();
        String name = splitter.name();
        System.out.println(name);
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
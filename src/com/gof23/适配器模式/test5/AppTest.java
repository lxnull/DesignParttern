package com.gof23.适配器模式.test5;

public class AppTest {

    public static void main(String[] args) {
//        Filter f1 = new LowPath(1);
//        System.out.println(f1.name());
//        System.out.println(f1.process(new Waveform()));
//
//        Filter f2 = new BandPath(1,2);
//        System.out.println(f2.name());
//        System.out.println(f2.process(new Waveform()));
        Apply.process(new Waveform(),new Adapter(new LowPath(1)));

        StingProcessor stingProcessor = new Upcase();
        Apply.process("abcd",stingProcessor);
    }
}

class Apply {
    public static void process(Object s,Processor processor) {
        System.out.println(processor.name());
        System.out.println(processor.process(s));
    }
}

interface Processor {
    public String name();

    Object process(Object input);
}

abstract class StingProcessor implements Processor {
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }
}

class Upcase extends StingProcessor {
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    public String process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class Downcase extends StingProcessor {
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    public String process(Object input) {
        return ((String)input).toLowerCase();
    }
}

class Splitter extends StingProcessor {
    // 协变：返回值类型是父类方法的子类，也算继承，参数类型不能变。
    @Override
    public String[] process(Object input) {
        return ((String)input).split(" ");
    }
}

//======================================================================================================================
/*
两个基于巧合过于相似的类
因为这两个类过于相似，所以之前我们为Processor类编写的工具类Apply也该也可以对Waveform使用，
但是因为Apply.process(Object s,Processor processor)方法的参数类型必须为Processor及其子类，所以我们要想办法进行改造，使得代码泛用性更强。
1.我们可以对Processor进行改造，将其从类变为接口，所有实现Processor接口的类都可以使用Apply.process()方法，
  但是我们发现的类可能是我们改变不了的类，我们手里可能只有它的字节码文件。所以这只是改造的第一步。
2.提取公用方法，每个子类中都有一个name()方法，重复太严重，我们应该通过继承一个类的方式去实现这个方法。抽象类实现Processor接口。
  但是现在依然无法解决最根本的问题：不能破坏开闭原则，不能改变别人的逻辑，我们还需要进行进一步修改。
3.适配器模式：跟据我们拥有的接口，去生成我们想要的接口。
  创建适配器类，适配器类实现Processor接口，与需要适配的类组合。
 */
//======================================================================================================================

// 适配器
class Adapter implements Processor {

    private Filter filter;

    public Adapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Object process(Object input) {
        return filter.process((Waveform) input);
    }
}

class Waveform {
    /*
    这种属性是为了模仿id自增，
    static变量在类加载时执行，加载时赋默认值0，只加载一次（因为static类型的变量为整个类全局的属性，只随类加载一次，且加载时只会赋默认值）
    类加载之后给id赋值，先赋值，再自增
     */
    private static int counter;
    private final int id = counter++;
    @Override
    public String toString() {
        return "Waveform{" +
                "id=" + id +
                '}';
    }
}
// 过滤器
class Filter {
    public String name() {
        return this.getClass().getSimpleName();
    }
    public Waveform process(Waveform input) {
        return input;
    }
}

class LowPath extends Filter {
    double cutoff;

    public LowPath(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        return input;// Dummy processing
    }
}

class BandPath extends Filter {
    double lowCutoff,highCutoff;

    public BandPath(double lowCutoff, double highCutoff) {
        this.lowCutoff = lowCutoff;
        this.highCutoff = highCutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        return input;// Dummy processing
    }
}
package com.gof23.动态代理.test8;

public class Apptest {

    public static void main(String[] args) {
        BookParser bp = new BookParser();
        BookParser proxy = new BookParserProxy(bp);
        Integer num = proxy.numberOfAdverb();
        System.out.println(num);
        Integer num1 = proxy.numberOfAdverb();
        System.out.println(num1);
    }
}

// 图书解析器
class BookParser {
    // 接收一整本书的内容
    private String content;
    // 句子数量
    public Integer numberOfSentence() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return content.split("[.!?]").length;
    }
    // 动词数量
    public Integer numberOfVerb() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1234;
    }
    // 副词数量
    public Integer numberOfAdverb() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2345;
    }
}

/*
代理的目的：获得和原本对象相同的方法
 */
// BookParser的代理类
class BookParserProxy extends BookParser {
    /*
    代理模式特点：与目标对象有has a关系
    在即使没有实现相同的接口，也可以实现代理模式。
    代理对象在调用方法时，调用的同样是被代理对象的方法.
    在调用被代理对象的方法时，代理对象同样对被代理对象的方法进行控制，
    比如下面的代码：实现了一个类似缓存功能，属性值为null时调用被代理对象的方法，不为null时不调用，直接返回存储的结果。
     */
    private BookParser bp;

    private Integer sentence;
    private Integer verb;
    private Integer adverb;

    public BookParserProxy(BookParser bookParser) {
        this.bp = bookParser;
    }

    @Override
    public Integer numberOfSentence() {
        if (sentence == null) {
            sentence = bp.numberOfSentence();
        }
        return sentence;
    }

    @Override
    public Integer numberOfVerb() {
        if (verb == null) {
            verb = bp.numberOfVerb();
        }
        return verb;
    }

    @Override
    public Integer numberOfAdverb() {
        if (adverb == null) {
            adverb = bp.numberOfAdverb();
        }
        return adverb;
    }
}

/*
学习了如此多的模式之后，我们会发现有的模式写法和架构其实大同小异，就比如现在的代理模式与适配器模式，那么这两种模式之间有什么不同呢？
1.代理模式中，被代理的对象和代理对象必须实现相同的接口；适配器模式中，适配器和被适配的对象则不用。
2.代理模式中，代理对象有权限控制被代理对象的方法是否运行；适配器模式中，被适配的对象一定要调用适配器所适配的功能，不然用这个模式没有意义。

所以，即使代码或者结构有很高的相似度，但用法和需求上的不同决定了他们根本上的不同。
 */
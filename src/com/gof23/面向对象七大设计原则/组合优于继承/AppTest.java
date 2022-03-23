package com.gof23.面向对象七大设计原则.组合优于继承;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

//需求：记录在集合中曾经存放过多少元素

public class AppTest {

    public static void main(String[] args) {

        MySet set = new MySet();
        set.add("a");
        set.add("a");
        set.add("a");
        System.out.println(set.getCount());
    }
}

// 方案一：继承HashSet类，重写add()方法
// 缺点：这种方法必须依赖于一个事实，HashSet的addAll()方法必须调用add()方法，
//      如果在将来的jdk版本中addAll()不再调用add()，则我们自定义的类失效。
//      例如HashMap，1.6，1.7，1.8三个版本底层分别被改变了三次，最终变为了现在的哈希表 + 红黑树结构。
class MySet extends HashSet {

    private int count = 0;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    public int getCount() {
        return count;
    }
}

// 方案二：继承HashSet类，重写add()方法 + addAll()方法
// 缺点：1.如果在新的版本中新增加了一个元素进入集合的方法addSome()，我们的类根本没有重写addSome()，
//      这样在新的版本中有人通过我们的类构造的对象调用addSome()方法添加元素时，并不会计数。
//      2.重写add()方法 + addAll()方法，要知道在HashSet底层中难免有一些类会依赖于add()，addAll()方法
//      我们这样没头没脑的重写他人的方法就会导致其它依赖于这几个方法的方法崩溃。
class MySet2 extends HashSet {

    private int count = 0;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        boolean modified = false;
        for (Object o:c) {
            add(c);
            modified = true;
        }
        return modified;
    }

    public int getCount() {
        return count;
    }
}

// 方案三：不再重写add()方法 + addAll()方法，
//        新建add2()方法 + addAll2()方法，
//        当需要add()方法 + addAll()时调用add()2方法 + addAll()2.
// 缺点：如果新版本中出现两个方法和我们新建的两个方法同名的新方法，用户需要调官方提供的方法，却调用成了我们的方法。
// 继承pass
class MySet3 extends HashSet {

    private int count = 0;

    public boolean add2(Object o) {
        count++;
        return super.add(o);
    }

    public boolean addAll2(Collection c) {
        boolean modified = false;
        for (Object o:c) {
            add2(c);
            modified = true;
        }
        return modified;
    }

    public int getCount() {
        return count;
    }
}

// 方案四：不继承HashSet，让MySet和HashSet组合，发生关联关系。
// 有选择的使用，不要无脑继承全部方法。
class MySet4 {

    private int count = 0;

    private Set set = new HashSet();

    public boolean add(Object o) {
        count++;
        return set.add(o);
    }

    public boolean addAll(Collection c) {
        count += c.size();
        return set.addAll(c);
    }

    public int getCount() {
        return count;
    }
}
package com.gof23.组合模式.test4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AppTest {

    public static void main(String[] args) {
        MenuComponent menu = new Menu("铁锅炖大鹅","地道东北家常风味");
        MenuComponent menu1 = new Menu("铁锅炖","食材自选");
        menu1.add(new MenuItem("大鹅土豆","xxxxxxx",false,100));
        menu1.add(new MenuItem("大鹅酸菜","xxxxxxx",false,110));
        menu1.add(new MenuItem("排骨豆角土豆","xxxxxxx",false,120));

        MenuComponent menu2 = new Menu("家常菜","经典风味");
        menu2.add(new MenuItem("锅包肉","老式，番茄汁",false,40));
        menu2.add(new MenuItem("乱炖","五花肉，红烧肉罐头",false,32));
        menu2.add(new MenuItem("aaaa","xxxxx",true,20));
        menu2.add(new MenuItem("bbbb","xxxxx",true,16));

        menu.add(menu1);
        menu.add(menu2);

//        menu.print("");
//        Menu.printV(menu);
        CompositeIterator iterator = menu.iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            System.out.println(next.getName() + "————" + next.getDescription());
        }
    }
}

/*
缺点：
1.用户创建菜单时必须知道Menu和MenuItem的存在，我们的目的是客户端只知道MenuComponent存在的情况下就能创建菜单。
2.底层的数据结构可控，不是写死的。

组合设计模式：
将对象组合成树状结构用来表示“部分——整体”之间的层次结构，组合模式使得用户对单个对象和组合对象的使用具有一致性。
组合模式主要在于理解清楚“部分/整体”还有“单个对象”与“组合对象”的含义。
在本例中就是消除菜单项和菜单间的区别。
 */

// 菜单共性
// 为了保证菜单和菜单项的一致性，我们要把这两个类需要的方法在父类中统统定义出来。
abstract class MenuComponent {
    private String name;
    private String description;

    public MenuComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void print(String prefix);

    // 属于菜单的方法(例如菜单项crud)
    public void add(MenuComponent mc) {
        // 不支持操作异常
        throw new UnsupportedOperationException();
    }

    public List<MenuComponent> getList() {
        throw new UnsupportedOperationException();
    }

    // 属于菜单项的方法
    public int getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    public CompositeIterator iterator() {
        throw new UnsupportedOperationException();
    }
}

// 菜单
class Menu extends MenuComponent {
    // 菜单项
    private List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }

    @Override
    public List<MenuComponent> getList() {
        return list;
    }

    @Override
    public void add(MenuComponent mc) {
        list.add(mc);
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + "《" + getName() + "》" + getDescription());
        Iterator<MenuComponent> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            // print（）方法为父类的抽象方法，所以不用判断迭代出来的对象类型
            next.print(prefix + "\t");
        }
    }

    public static void printV(MenuComponent mc) {
        Iterator<MenuComponent> iterator = mc.getList().iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            try {
                if (next.isVegetarian()) {
                    next.print("");
                }
            } catch (Exception e) {
                printV(next);
            }
        }
    }

    public CompositeIterator iterator() {
        return new CompositeIterator(list.iterator());
    }
}

// 菜单项
class MenuItem extends MenuComponent {
    private boolean vegetarian;
    private int price;

    public MenuItem(String name, String description,boolean vegetarian,int price) {
        super(name, description);
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public boolean isVegetarian() {
        return this.vegetarian;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName() + " : " + getDescription());
    }

}

class CompositeIterator implements Iterator<MenuComponent> {
    private Stack<Iterator<MenuComponent>> s = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> it) {
        s.push(it);
    }

    @Override
    public boolean hasNext() {
        if (s.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComponent> it = s.peek();
            if (!it.hasNext()) {
                s.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public MenuComponent next() {
        Iterator<MenuComponent> it = s.peek();
        MenuComponent mc = it.next();

        if (mc instanceof Menu) {
            s.push(mc.getList().iterator());
        }
        return mc;
    }
}
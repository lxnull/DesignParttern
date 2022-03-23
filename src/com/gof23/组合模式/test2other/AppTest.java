package com.gof23.组合模式.test2other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AppTest {

    public static void main(String[] args) {
        MenuComponent menu = new Menu("东北菜","地道东北家常风味");
        MenuComponent menu1 = new Menu("铁锅炖","食材自选");

        MenuComponent menuItem = new MenuItem("锅包肉","老式，番茄汁",false);
        MenuComponent menuItem1 = new MenuItem("乱炖","五花肉，红烧肉罐头",false);
        MenuComponent menuItem2 = new MenuItem("大鹅土豆", "xxxxxxx",false);
        MenuComponent menuItem3 = new MenuItem("大鹅酸菜", "xxxxxxx",false);
        MenuComponent menuItem4 = new MenuItem("排骨豆角土豆", "xxxxxxx",false);
        MenuComponent v = new MenuItem("烧芸豆","xxxxx",true);
        MenuComponent v1 = new MenuItem("烧青菜","xxxxx",true);

        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu1.add(menuItem4);

        menu.add(menu1);
        menu.add(menuItem);
        menu.add(menuItem1);
        menu.add(v);
        menu.add(v1);

//        menu.print("");
//        Menu.printV((Menu) menu);

        CompositeIterator iterator = menu.iterator();

        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            System.out.println(next.getName() + " : " + next.getDescription());
        }
    }
}

// 菜单共性
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

    // 菜单专有的方法
    public List<MenuComponent> getList() {
        throw new UnsupportedOperationException();
    }

    public void add(MenuComponent mc) {
        throw new UnsupportedOperationException();
    }

    public CompositeIterator iterator() {
        throw new UnsupportedOperationException();
    }

    // 菜单项专用的方法
    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    // 具体实现不同的抽象方法
    public abstract void print(String prefix);
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

    public static void printV(Menu menu) {
        Iterator<MenuComponent> iterator = menu.getList().iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            if (next instanceof MenuItem) {
                if (next.isVegetarian()) {
                    next.print("");
                }
            } else {
                printV((Menu) next);
            }
        }
    }

    @Override
    public CompositeIterator iterator() {
        return new CompositeIterator(list.iterator());
    }
}

// 菜单项
class MenuItem extends MenuComponent {

    private boolean vegetarian;

    public MenuItem(String name, String description, boolean vegetarian) {
        super(name, description);
        this.vegetarian = vegetarian;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName() + " : " + getDescription());
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }
}

// 迭代器
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
        MenuComponent next = it.next();

        if (next instanceof Menu) {
            s.push(next.getList().iterator());
        }

        return next;
    }
}
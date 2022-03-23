package com.gof23.组合模式.test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        Menu menu = new Menu("东北菜","地道东北家常风味");
        Menu menu1 = new Menu("铁锅炖","食材自选");
        menu1.add(new MenuItem("大鹅土豆","xxxxxxx"));
        menu1.add(new MenuItem("大鹅酸菜","xxxxxxx"));
        menu1.add(new MenuItem("排骨豆角土豆","xxxxxxx"));
        MenuItem menuItem = new MenuItem("锅包肉","老式，番茄汁");
        MenuItem menuItem1 = new MenuItem("乱炖","五花肉，红烧肉罐头");

        menu.add(menu1);
        menu.add(menuItem);
        menu.add(menuItem1);

        menu.print("");
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

    public abstract void print(String prefix);
}

// 菜单
class Menu extends MenuComponent {
    // 菜单项
    private List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }

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
}

// 菜单项
class MenuItem extends MenuComponent {

    public MenuItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName() + " : " + getDescription());
    }
}
package com.gof23.组合模式.test1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        Menu menu = new Menu("东北菜","地道东北家常风味");
        MenuItem menuItem = new MenuItem("铁锅炖","五花肉，河鱼，大鹅，小母鸡，排骨任选组合");
        MenuItem menuItem1 = new MenuItem("锅包肉","老式，番茄汁");
        MenuItem menuItem2 = new MenuItem("乱炖","五花肉，红烧肉罐头");
        menu.add(menuItem);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.print("");

        // menuItem.add("大锅");
        // menuItem.add("中锅");
        // 因为菜单项中没有add()方法，所以不能为菜单项添加子菜单
    }
}

// 菜单
class Menu {
    private String name;
    private String description;
    // 菜单项
    private List<MenuItem> list = new ArrayList<>();

    public Menu() {}

    public Menu(String name, String description) {
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

    public void add(MenuItem menuItem) {
        list.add(menuItem);
    }

    public void print(String prefix) {
        System.out.println("《" + name + "》" + "————" + description);
        Iterator<MenuItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            menuItem.print(prefix);
        }
    }
}

// 菜单项
class MenuItem {
    private String name;
    private String description;

    public MenuItem() {}

    public MenuItem(String name, String description) {
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

    public void print(String prefix) {
        System.out.println("\t" + prefix + name + " : " + description);
    }
}
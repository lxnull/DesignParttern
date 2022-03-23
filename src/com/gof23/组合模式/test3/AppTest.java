package com.gof23.组合模式.test3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        Menu menu = new Menu("铁锅炖大鹅","地道东北家常风味");
        Menu menu1 = new Menu("铁锅炖","食材自选");
        menu1.add(new MenuItem("大鹅土豆","xxxxxxx",false));
        menu1.add(new MenuItem("大鹅酸菜","xxxxxxx",false));
        menu1.add(new MenuItem("排骨豆角土豆","xxxxxxx",false));

        Menu menu2 = new Menu("家常菜","经典风味");
        menu2.add(new MenuItem("锅包肉","老式，番茄汁",false));
        menu2.add(new MenuItem("乱炖","五花肉，红烧肉罐头",false));
        menu2.add(new MenuItem("aaaa","xxxxx",true));
        menu2.add(new MenuItem("bbbb","xxxxx",true));

        menu.add(menu1);
        menu.add(menu2);

//        menu.print("");
        Menu.printV(menu);
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

    public List<MenuComponent> getList() {
        return list;
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

    public static void printV(Menu menu) {
        Iterator<MenuComponent> iterator = menu.getList().iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            if (next instanceof MenuItem) {
                if (((MenuItem) next).isVegetarian()) {
                    next.print("");
                }
            } else {
                printV((Menu) next);
            }
        }
    }
}

// 菜单项
class MenuItem extends MenuComponent {
    private boolean vegetarian;

    public MenuItem(String name, String description,boolean vegetarian) {
        super(name, description);
        this.vegetarian = vegetarian;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName() + " : " + getDescription());
    }
}
/*
缺点：
1.用户创建菜单时必须知道Menu和MenuItem的存在，我们的目的是客户端只知道MenuComponent存在的情况下就能创建菜单。
2.底层的数据结构可控，不是写死的。
 */
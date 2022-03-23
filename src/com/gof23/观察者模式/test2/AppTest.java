package com.gof23.观察者模式.test2;

import java.util.ArrayList;
import java.util.List;

/*
跟据之前提出的问题改造代码：
使观察者和被观察的类产生组合关系，达到观察者和被观察者之间相互包含，
接口中的抽象方法改为无参方法，使它能观察多个接口，
把接口从耦合中解脱处理，增强观察者和被观察者之间的耦合性。因为观察者类创建就是为了被观察的对象服务的，所以他们之间耦合性多强都不用在意。

如此依赖二者之间就实现了一个推拉的过程：
变化后，被观察的向观察者推送消息，通知观察者我变了，这一过程并不传递数据，
观察者在得知有变化后，通过组合好的属性拉取已经改变的值。
 */

public class AppTest {

    public static void main(String[] args) {
        Role r = new Role(100, 100, "勇者");
        r.addObserver(new Panel(r));
        r.addObserver(new BallPanel(r));
        r.addObserver(new HeadPanel(r));
        Monster monster = new Monster();
        monster.attack(r);
        r.notifyObservers();
    }
}

class Role {

    private int hp;
    private int mp;
    private String name;
    private List<Observer> olist = new ArrayList<>();

    public Role() {}

    public Role(int hp, int mp, String name) {
        this.hp = hp;
        this.mp = mp;
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Observer> getOlist() {
        return olist;
    }

    // 对于观察者的方法
    public boolean addObserver(Observer o) {
        boolean res = olist.add(o);
        return res;
    }

    public Observer removeObserver(int i) {
        Observer remove = olist.remove(i);
        return remove;
    }

    public void notifyObservers() {
        for (Observer observer : olist) {
            observer.update();
        }
    }
}

class Monster {

    public void attack(Role role) {
        role.setHp(role.getHp() - 10);
        System.out.println("怪兽的攻击使你失去10点血量");
    }
}

interface Observer {
    // 更新数据的方法
    public void update();
}

class Panel implements Observer {

    private Role r;

    public Panel(Role r) {
        this.r = r;
    }

    @Override
    public void update() {
        System.out.println("修改后的hp：" + r.getHp());
    }
}

class BallPanel implements Observer {

    private Role r;

    public BallPanel(Role r) {
        this.r = r;
    }

    @Override
    public void update() {
        System.out.println("修改后的hp：" + r.getHp());
    }
}

class HeadPanel implements Observer {

    private Role r;

    public HeadPanel(Role r) {
        this.r = r;
    }

    @Override
    public void update() {
        System.out.println("修改后的hp：" + r.getHp());
    }
}
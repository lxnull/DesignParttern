package com.gof23.观察者模式.test1;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        Role r = new Role(100, 100, "勇者");
        r.addObserver(new Panel());
        r.addObserver(new BallPanel());
        r.addObserver(new HeadPanel());
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
            observer.update(this.getHp());
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
    public void update(int hp);
}

class Panel implements Observer {

    @Override
    public void update(int hp) {
        System.out.println("修改后的hp：" + hp);
    }
}

class BallPanel implements Observer {

    @Override
    public void update(int hp) {
        System.out.println("修改后的hp：" + hp);
    }
}

class HeadPanel implements Observer {

    @Override
    public void update(int hp) {
        System.out.println("修改后的hp：" + hp);
    }
}

/*
当前的代码已经具有观察者设计模式的雏形，但仍具有以下几点的问题：
1.应对需要观察的数据改变的情况，更改太过于复杂。
2.如果因为上面的原因不传入具体的值，把观察的对象整个放进去，则抽象的接口依赖了具体的类，违反了依赖倒置原则。
 */
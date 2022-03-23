package com.gof23.原型模式.shen3;

import java.io.Serializable;

public class Emp implements Cloneable, Serializable {
    private int id;

    private String name;

    public Emp() {

    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Emp clone() throws CloneNotSupportedException {
        return (Emp) super.clone();
    }
}
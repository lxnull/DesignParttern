package com.gof23.面向对象七大设计原则.里氏替换原则;

public class Test {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(10);
        rectangle.setWidth(15);

        test(rectangle);

        Rectangle rectangle1 = new Square();
        rectangle1.setWidth(10);
//        rectangle1.setHeight(6);

        System.out.println(rectangle1.getHeight());

//        test(rectangle1);
    }

    public static void test(Rectangle r) {
        while (r.getHeight() <= r.getWidth()) {

            System.out.println("长：" + r.getWidth() + "，宽：" + r.getHeight());

            r.setHeight(r.getHeight() + 1);
        }
    }
}

class Rectangle {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

class Square extends Rectangle {
    private int sidelength;

    @Override
    public int getWidth() {
        return this.sidelength;
    }

    @Override
    public void setWidth(int width) {
        this.sidelength = width;
    }

    @Override
    public int getHeight() {
        return this.sidelength;
    }

    @Override
    public void setHeight(int height) {
        this.sidelength = height;
    }
}

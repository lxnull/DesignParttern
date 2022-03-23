package com.gof23.面向对象七大设计原则.homework2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Test2 {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");

        stack.push("f");;
        System.out.println(stack.pop());

        System.out.println(stack.size());

        for (int i = 0; i < stack.size(); i++) {
            stack.pop();
        }
    }
}

class Stack<T> {

    private LinkedList<T> list = new LinkedList<>();

    int size = 0;

    public void push(T t) {
        list.add(t);
        size++;
    }

    public T pop() {
        size--;
        return list.removeLast();
    }

    public int size() {
        return this.size;
    }
}

package com.gof23.面向对象七大设计原则.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {

        String s = fileLoad("a.txt");

        String[] strings = strategy(s, "[^a-zA-Z]+");

//        for (String str:strings) {
//            System.out.println(str);
//        }

//        System.out.println(strings.length);

        String s1 = fileLoad("b.txt");

        String[] strings1 = strategy(s1, "[^a-zA-Z]+");

//        for (String str1:strings1) {
//            System.out.println(str1);
//        }

//        System.out.println(strings1.length);

        String[] sameElement = getSameElement(strings, strings1);

        for (int i = 0; i < sameElement.length; i++) {
            System.out.println(sameElement[i]);
        }

        System.out.println("===================================================================");

        // 用集合的方式实现功能
        List<String> list = Arrays.asList(strings);// 返回的是一个只读集合
        List<String> nlist = new ArrayList<>(list);// 只读集合转换为普通集合
        List<String> list1 = Arrays.asList(strings1);
        List<String> nlist1 = new ArrayList<>(list1);
        String[] sameElement1 = getSameElement(nlist, nlist1);
        for (int i = 0; i < sameElement1.length; i++) {
            System.out.println(sameElement1[i]);
        }
    }

    // 寻找相同的元素1
    private static String[] getSameElement(String[] strings, String[] strings1) {
        Set set = new HashSet();

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings1.length; j++) {
                if (strings[i].equals(strings1[j])) {
                    set.add(strings[i]);
                }
            }
        }

        return (String[]) set.toArray(new String[] {});
    }

    // 寻找相同的元素2
    private static String[] getSameElement(List list, List list1) {
        list.retainAll(list1);// 计算两个集合交集。结果存在list中

        return (String[]) list.toArray(new String[] {});
    }

    // 文件转换成字符串数组的方法
    private static String[] strategy(String str, String strategy) {
        String[] split = str.split(strategy);

        return split;
    }

    // 读文件的方法
    private static String fileLoad(String filename) throws IOException {
        FileReader fr = new FileReader(filename);

        BufferedReader br1 = new BufferedReader(fr);

        StringBuilder sb1 = new StringBuilder();// StringBuilder可以对字符串进行操作且不会产生新的字符串

        String line = null;

        while ((line = br1.readLine()) != null) {
            sb1.append(line);
            sb1.append("\n");
        }

        br1.close();
        return sb1.toString();
    }
}

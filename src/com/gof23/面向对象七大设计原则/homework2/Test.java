package com.gof23.面向对象七大设计原则.homework2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        try {
            String s = fileLoad("c.txt");
            String s1 = fileLoad("d.txt");

            String[] element = getElement(s, "[\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]+");
            String[] element1 = getElement(s1, "[\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]+");

            List<String> list = Arrays.asList(element);
            List<String> list1 = Arrays.asList(element1);

            List sameElement = getSameElement(list, list1);

            List union = getUnion(list, list1);

            float a = (float) sameElement.size()/union.size();
            String format = String.format("%.2f", a * 100);
            System.out.println(format+"%");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileLoad(String path) throws IOException {
        FileReader fileReader = new FileReader(path);

        BufferedReader buffer = new BufferedReader(fileReader);

        StringBuilder builder = new StringBuilder();

        String line = null;

        while ((line = buffer.readLine()) != null) {
            builder.append(line);
        }

        buffer.close();
        fileReader.close();

        return builder.toString();
    }

    // 获得字符串数组
    public static String[] getElement(String str, String strategy) {
        String[] split = str.split(strategy);

        return split;
    }

    // 获得数组中相同的元素，返回集合
    public static List getSameElement(List list, List list1) {
        List nlist = new ArrayList(list);
        List nlist1 = new ArrayList(list1);

        nlist.retainAll(nlist1);

        return nlist;
    }

    // 获得并集
    public static List getUnion(List list, List list1) {
        List nlist = new ArrayList(list);
        List nlist1 = new ArrayList(list1);

        nlist.addAll(nlist1);
        HashSet set = new HashSet(nlist);
        List res = new ArrayList();

        for (Object s:set) {
            res.add(s);
        }

        return res;
    }
}
package com.gof23.面向对象七大设计原则.homework3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileHomework {

    public static void main(String[] args) {
        try {
            // 加载
            String s = fileLoader("f.txt");
            // 解析单词
            List<String> passwords = getPasswords(s);
            // 统计单词数目
            Map<String, Integer> map = countPassword(passwords);
//            for (Map.Entry<String, Integer> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + ":" + entry.getValue());
//            }
            // 得到最大的value
            int maxValue = getMaxValue(map);
            // 获取最大的key
            List<String> valueByKey = getValueByKey(maxValue, map);
            for (String maxKey : valueByKey) {
                System.out.println(maxKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 把文件的内容加到字符串里
    public static String fileLoader(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder builder = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        // 关流的时候只关上层流，底层流会自动关闭
        br.close();
        return builder.toString();
    }

    // 把单词分割成数组
    public static List<String> getPasswords(String str) {
        List<String> ps = new ArrayList<String>();
        String[] split = str.split("[^a-zA-Z]+");
        return ps = Arrays.asList(split);
    }

    // 统计单词数量
    public static Map<String,Integer> countPassword(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            if (map.containsKey(s)) {
                Integer count = map.get(s);
                map.put(s,count+1);
            } else {
                map.put(s,1);
            }
        }
        return map;
    }

    // 获取最大的次数
    public static int getMaxValue(Map<String,Integer> map) {
        Collection<Integer> values = map.values();
        return Collections.max(values);
    }

    // 跟据value获取key
    public static List<String> getValueByKey(int value, Map<String,Integer> map) {
        List list = new ArrayList();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
}

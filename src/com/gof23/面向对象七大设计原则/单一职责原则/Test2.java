package com.gof23.面向对象七大设计原则.单一职责原则;

import java.io.*;

// 符合单一职责原则的设计方案
public class Test2 {

    public static void main(String[] args) {
        try {
            String s = loadFile("a.txt");
//            String[] extracted = extracted(s,"[^a-zA-Z]+");
//            String[] extracted = extracted(s,"\n");
            String[] extracted = extracted(s," ");
            for (String str:extracted) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 只负责分割字符串（需要处理的字符串，切割策略）
    public static String[] extracted(String str, String str2) {
        String[] split = str.split(str2);
        return split;
    }

    // 只负责读取文件，获得字符串（文件路径）
    public static String loadFile(String path) throws Exception {
        Reader in = new FileReader(path);
        int count = 0;
        String line = null;
        BufferedReader br = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
            count++;
        }
        br.close();
        in.close();

        return sb.toString();
    }
}

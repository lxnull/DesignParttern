package com.gof23.面向对象七大设计原则.单一职责原则;

/*
    * 单一职责原则：每个方法，每个类，每个框架都只负责一件事。
*
* 以下代码违反了单一职责原则，即负责读取文件，又负责分割字符串，复用性低。
* 如果新有一个获得文件中句子的数的代码，我们需要重新写文件加载到字符串中的代码
* */

import java.io.*;

public class Test {

    public static void main(String[] args) {

        Reader in = null;

        try {
             in = new FileReader("a.txt");
             int count = 0;

//           一个一个字符读
//             while (in.read() != -1) {
//                 count++;
//             }
//            System.out.println(count);

//          一行一行读
//            BufferedReader br = new BufferedReader(in);
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                count++;
//            }
//            System.out.println(count);

            String line = null;
            BufferedReader br = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                count++;
            }
            br.close();
            String[] split = sb.toString().split("[^a-zA-Z]+");
            for (String s:split) {
                System.out.println(s);
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

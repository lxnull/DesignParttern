package com.gof23.装饰器设计模式.test5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MyBufferReaderTest {

    public static void main(String[] args) throws Exception {
        Reader fileReader = new FileReader("e.txt");
//        MyBufferReader mbr = new MyBufferReader(fileReader);
//
//        String str = mbr.readLine();
//        System.out.println(str);
        MyLineNumberReader numberReader = new MyLineNumberReader(fileReader);
        String s;
        while ((s = numberReader.readLine()) != null) {
            System.out.println(numberReader.getReadNum() + "." + s);
        }
    }
}

class MyBufferReader extends Reader {

    private Reader reader;

    public MyBufferReader(Reader reader) {
        this.reader = reader;
    }

    public String readLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        int n;
        // 循环读。读到换行符停止
        while (true) {
            n = reader.read();
            if (n == '\r') {
                continue;
            }
            if (n == '\n' || n == -1) {
                break;
            }
            builder.append((char) n);
        }
        if (builder.toString().length() == 0) {
            if (n == '\n') {
                return "";// 当长度为0，但读到换行符时，证明还未读完，返回空
            }
            return null;// 当长度为0，且读到-1时，证明已经读完，返回null
        }
        return builder.toString();
    }

    // 空方法，不实现
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

// 获得行号
class MyLineNumberReader extends MyBufferReader {

    private int readNum;

    public MyLineNumberReader(Reader reader) {
        super(reader);
    }

    // 重写父类读取行的方法
    @Override
    public String readLine() throws IOException {
        String s = super.readLine();
        if (s != "null") {
            readNum++;
        }
        return s;
    }

    // 单一职责，把获取行号的方法单独提取出来
    public int getReadNum() {
        return readNum;
    }
}

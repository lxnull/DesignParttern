package com.gof23.原型模式.shen2;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class WeekReport implements Cloneable, Serializable {

    private int id;

    private Emp emp;

    private String summary;

    private String plain;

    private String suggestion;

    private Calendar createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WeekReport{" +
                "id=" + id +
                ", emp='" + emp + '\'' +
                ", summary='" + summary + '\'' +
                ", plain='" + plain + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    protected WeekReport clone() throws CloneNotSupportedException {
        try {
            FileOutputStream stream = new FileOutputStream("obj.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
            objectOutputStream.writeObject(this);// 序列化时会自动处理对象的所有层级关系
            objectOutputStream.close();

            FileInputStream stream1 = new FileInputStream("obj.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(stream1);
            WeekReport clone = (WeekReport) objectInputStream.readObject();
            objectInputStream.close();

            return clone;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WeekReport getWeekReport() {
        WeekReport weekReport = new WeekReport();

        // 默认员工
        Emp zs = new Emp(1, "张三");
        weekReport.setEmp(zs);

        // 创建时间
        Calendar calendar = Calendar. getInstance();
        Date date = new Date();
        calendar.setTime(date);
        weekReport.setCreateTime(calendar);

        // 问题描述
        weekReport.setSuggestion("本周未发现任何异常情况");

        return weekReport;
    }
}
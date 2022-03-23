package com.gof23.原型模式.shen3;

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
            ByteArrayOutputStream stream = new ByteArrayOutputStream();// 在内存中开辟空间的流
            ObjectOutputStream os = new ObjectOutputStream(stream);
            os.writeObject(this);// 序列化时会自动处理对象的所有层级关系
            os.close();
            /*
            对象被写在os里（序列化对象流），而os会存储在stream中（在内存中开辟的空间）
             */
            byte[] bytes = stream.toByteArray();

            ByteArrayInputStream stream1 = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(stream1);
            WeekReport clone = (WeekReport) is.readObject();

            return clone;
        } catch (Exception e) {
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
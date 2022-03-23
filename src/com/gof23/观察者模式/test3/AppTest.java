package com.gof23.观察者模式.test3;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();

        ws.addObserver(new Phone(ws));
        ws.addObserver(new Computer(ws));

        ws.setData(6,49,1005);
        ws.notifyObserver();
        ws.setTemperature(1);
        ws.notifyObserver();
    }
}

class WeatherStation {

    private Integer temperature;// 温度
    private Integer humidity;// 湿度
    private Integer pressure;// 气压
    private List<Observer> olist = new ArrayList<>();

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public void setData(Integer temperature, Integer humidity, Integer pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void addObserver(Observer observer) {
        olist.add(observer);
    }

    public void removeObserver(int i) {
        olist.remove(i);
    }

    public void notifyObserver() {
        for (Observer observer : olist) {
            observer.update();
        }
    }
}

interface Observer {
    public void update();
}

class Phone implements Observer {

    private WeatherStation ws;

    public Phone(WeatherStation ws) {
        this.ws = ws;
    }

    @Override
    public void update() {
        System.out.println("当前的温度是：" + ws.getTemperature());
        System.out.println("当前的湿度是：" + ws.getHumidity());
        System.out.println("当前的气压是：" + ws.getPressure());
    }
}

class Computer implements Observer {

    private WeatherStation ws;

    public Computer(WeatherStation ws) {
        this.ws = ws;
    }

    @Override
    public void update() {
        System.out.println("当前的温度是：" + ws.getTemperature());
        System.out.println("当前的湿度是：" + ws.getHumidity());
        System.out.println("当前的气压是：" + ws.getPressure());
    }
}
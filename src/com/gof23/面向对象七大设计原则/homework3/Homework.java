package com.gof23.面向对象七大设计原则.homework3;

public class Homework {

    public static void main(String[] args) {
        Director director = new Director(new BenzBuilder());
        Car car = director.getCar();
        System.out.println(car.toString());
    }
}

class Car {
    private String carDoor;
    private String carWindow;
    private String carBody;
    private String engine;

    public Car() {

    }

    public Car(String carDoor, String carWindow, String carBody, String engine) {
        this.carDoor = carDoor;
        this.carWindow = carWindow;
        this.carBody = carBody;
        this.engine = engine;
    }

    public String getCarDoor() {
        return carDoor;
    }

    public void setCarDoor(String carDoor) {
        this.carDoor = carDoor;
    }

    public String getCarWindow() {
        return carWindow;
    }

    public void setCarWindow(String carWindow) {
        this.carWindow = carWindow;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carDoor='" + carDoor + '\'' +
                ", carWindow='" + carWindow + '\'' +
                ", carBody='" + carBody + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }
}

interface CarBuilder {
    public void carDoor();
    public void carWindow();
    public void carBody();
    public void engine();

    public Car getCar();
}

class BMWBuilder implements CarBuilder {

    private Car car = new Car();

    @Override
    public void carDoor() {
        car.setCarDoor("宝马车门");
    }

    @Override
    public void carWindow() {
        car.setCarWindow("宝马车窗");
    }

    @Override
    public void carBody() {
        car.setCarBody("宝马车身");
    }

    @Override
    public void engine() {
        car.setEngine("宝马发动机");
    }

    @Override
    public Car getCar() {
        return car;
    }
}

class BenzBuilder implements CarBuilder {

    private Car car = new Car();

    @Override
    public void carDoor() {
        car.setCarDoor("奔驰车门");
    }

    @Override
    public void carWindow() {
        car.setCarWindow("奔驰车窗");
    }

    @Override
    public void carBody() {
        car.setCarBody("奔驰车身");
    }

    @Override
    public void engine() {
        car.setEngine("奔驰发动机");
    }

    @Override
    public Car getCar() {
        return car;
    }
}

class Director {
    public CarBuilder carBuilder;

    public Director(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car getCar() {
        carBuilder.carBody();
        carBuilder.carDoor();
        carBuilder.engine();
        carBuilder.carWindow();
        return carBuilder.getCar();
    }
}
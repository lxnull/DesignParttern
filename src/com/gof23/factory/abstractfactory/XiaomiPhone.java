package com.gof23.factory.abstractfactory;

public class XiaomiPhone implements IphoneProduct {
    @Override
    public void callUp() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("小米手机发短信");
    }
}

package com.gof23.factory.abstractfactory;

public class HuaweiPhone implements IphoneProduct {
    @Override
    public void callUp() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为手机发短信");
    }
}

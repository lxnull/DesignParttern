package com.gof23.factory.abstractfactory;

public class XiaomiRouter implements IrouterProduct {
    @Override
    public void start() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void shutDown() {
        System.out.println("小米路由器关机");
    }
}

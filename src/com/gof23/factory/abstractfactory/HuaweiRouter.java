package com.gof23.factory.abstractfactory;

public class HuaweiRouter implements IrouterProduct {
    @Override
    public void start() {
        System.out.println("华为路由器开机");
    }

    @Override
    public void shutDown() {
        System.out.println("华为路由器关机");
    }
}

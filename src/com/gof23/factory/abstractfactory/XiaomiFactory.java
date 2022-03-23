package com.gof23.factory.abstractfactory;

public class XiaomiFactory implements IproductFectory {
    @Override
    public IphoneProduct getPhone() {
        return new XiaomiPhone();
    }

    @Override
    public IrouterProduct getRouter() {
        return new XiaomiRouter();
    }
}

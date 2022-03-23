package com.gof23.factory.abstractfactory;

public class HuaweiFactory implements IproductFectory {

    @Override
    public IphoneProduct getPhone() {
        return new HuaweiPhone();
    }

    @Override
    public IrouterProduct getRouter() {
        return new HuaweiRouter();
    }
}

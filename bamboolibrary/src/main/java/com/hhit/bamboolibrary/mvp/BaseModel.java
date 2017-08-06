package com.hhit.bamboolibrary.mvp;

import com.hhit.bamboolibrary.intermediate.IServiceManager;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public class BaseModel implements IModel{
    protected IServiceManager mServiceManager;

    public BaseModel(IServiceManager serviceManager) {
        this.mServiceManager = serviceManager;
    }

    @Override
    public void onDestroy() {
        this.mServiceManager = null;
    }
}

package com.hhit.bamboomvp.home.model;

import com.hhit.bamboolibrary.di.scope.ActivityScope;
import com.hhit.bamboolibrary.intermediate.IServiceManager;
import com.hhit.bamboolibrary.mvp.BaseModel;
import com.hhit.bamboomvp.home.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 2017/8/6.
 */
@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Inject
    public HomeModel(IServiceManager serviceManager) {
        super(serviceManager);
    }
}

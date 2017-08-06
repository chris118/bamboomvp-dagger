package com.hhit.bamboolibrary.base;

import android.app.Application;

import com.hhit.bamboolibrary.di.AppModule.AppModule;
import com.hhit.bamboolibrary.di.component.AppComponent;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public class BaseApplication extends Application{

    private Application mApplication;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(mApplication))//提供application
                .build();
        mAppComponent.inject(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppComponent getAppComponent() {

        this.mAppComponent = null;

        return mAppComponent;
    }
}

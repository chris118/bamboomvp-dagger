package com.hhit.bamboolibrary.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.hhit.bamboolibrary.di.AppModule.AppModule;
import com.hhit.bamboolibrary.di.AppModule.HttpModule;
import com.hhit.bamboolibrary.intermediate.IServiceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/8/6.
 *
 * 注入全局实例
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    Application application();

    Gson gson();

    IServiceManager serviceManager();
}

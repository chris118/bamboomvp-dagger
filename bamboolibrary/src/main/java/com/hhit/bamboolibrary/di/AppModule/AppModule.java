package com.hhit.bamboolibrary.di.AppModule;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hhit.bamboolibrary.intermediate.IServiceManager;
import com.hhit.bamboolibrary.intermediate.ServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/8/6.
 *
 * 注入全局实例
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.mApplication;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    public IServiceManager provideServiceManager(ServiceManager serviceManager) {
        return serviceManager;
    }

}

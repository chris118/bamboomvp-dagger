package com.hhit.bamboolibrary.di.AppModule;

/**
 * Created by xiaopeng on 2017/8/8.
 */

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供网络相关
 */
@Module
public class HttpModule {
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
       return new OkHttpClient();
    }
}

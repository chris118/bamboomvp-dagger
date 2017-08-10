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

@Module
public class HttpModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient,如果不设置会提供一个默认的
                .client(new OkHttpClient())
                .baseUrl("https://api.github.com/")//设置baseUrl
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//添加Gson转换器
                .build();
        return  retrofit;
    }
}

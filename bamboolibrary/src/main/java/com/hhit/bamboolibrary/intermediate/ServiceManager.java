package com.hhit.bamboolibrary.intermediate;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by xiaopeng on 2017/8/6.
 */

@Singleton
public class ServiceManager implements IServiceManager {

    private Retrofit mRetrofit;

    @Inject
    public ServiceManager(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public <T> T createRetrofitService(Class<T> service) {
        T retrofitService;
        retrofitService = mRetrofit.create(service);
        return retrofitService;
    }
}

package com.hhit.bamboolibrary.intermediate;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaopeng on 2017/8/6.
 */

/**
 *
 * 提供默认的base_url, app也可以使用不同的base_url进行调用
 */
@Singleton
public class ServiceManager implements IServiceManager {
    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;

    @Inject
    public ServiceManager(Gson gson, OkHttpClient okHttpClient) {
        mGson = gson;
        mOkHttpClient = okHttpClient;
    }

    @Override
    public <T> T createRetrofitService(Class<T> service) {
        T retrofitService;
        Retrofit retrofit = RetrofitFactory.create("https://api.github.com/");
        retrofitService = retrofit.create(service);
        return retrofitService;
    }

    @Override
    public <T> T createRetrofitService(Class<T> service, String base_url) {
        T retrofitService;
        Retrofit retrofit = RetrofitFactory.create(base_url);
        retrofitService = retrofit.create(service);
        return retrofitService;
    }

    private static class RetrofitFactory {
        public static Retrofit create(String baseUrl) {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .baseUrl(baseUrl)
                    .client(mOkHttpClient)
                    .build();
        }
    }
}

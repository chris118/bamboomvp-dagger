package com.hhit.bamboolibrary.intermediate;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public interface IServiceManager {
    <T> T createRetrofitService(Class<T> service);
    <T> T createRetrofitService(Class<T> service, String base_url);
}

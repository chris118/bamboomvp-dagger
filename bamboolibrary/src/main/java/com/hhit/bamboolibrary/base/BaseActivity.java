package com.hhit.bamboolibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.mvp.IPresenter;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 注入Presenter: Presenter派生类的构造函数添加 @Inject 注解
     */
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Application application =  getApplication();
//        AppComponent appComponent = application.getAppComponent();
//        injectComponent(appComponent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void injectComponent(AppComponent appComponent);
}

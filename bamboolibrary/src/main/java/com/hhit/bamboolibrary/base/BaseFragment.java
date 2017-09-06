package com.hhit.bamboolibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.mvp.IPresenter;
import com.hhit.bamboolibrary.utils.EventBusUtil;
import com.trello.rxlifecycle.components.support.RxFragment;
import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xiaopeng on 2017/8/20.
 */

public abstract class BaseFragment <P extends IPresenter> extends RxFragment {

    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    /**
     * 注入Presenter: Presenter派生类的构造函数添加 @Inject注解 完成注入
     */
    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return onViewCreate(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseApplication app =  (BaseApplication)this.getActivity().getApplication();
        AppComponent appComponent = app.getAppComponent();
        injectComponent(appComponent);

        mUnbinder = ButterKnife.bind(this.getActivity());
        EventBusUtil.register(this);
        initialized();
        onCreatedView(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }

        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            this.mUnbinder = null;
        }

        EventBusUtil.unregister(this);
    }

    /**
     * 子类实现
     */
    protected abstract View onViewCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected abstract void onCreatedView(View view, @Nullable Bundle savedInstanceState);


    /**
     * 子类初始化数据
     */
    protected abstract void initialized();

    /**
     * 子类 初始化 依赖注入
     * @param appComponent 把全局appComponent传送给子类
     */
    protected abstract void injectComponent(AppComponent appComponent);
}

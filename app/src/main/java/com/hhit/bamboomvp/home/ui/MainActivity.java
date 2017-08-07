package com.hhit.bamboomvp.home.ui;

import android.util.Log;

import com.hhit.bamboolibrary.base.BaseActivity;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.di.component.DaggerMainComponent;
import com.hhit.bamboomvp.home.di.module.MainModule;
import com.hhit.bamboomvp.home.presenter.HomePresenter;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * HomeContract.View & HomeContract.Model 注入
     * HomePresenter 由 BaseActivity 注入
     * @param appComponent
     */
    @Override
    protected void injectComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        Log.d(TAG, mPresenter.toString());
    }
}

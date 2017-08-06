package com.hhit.bamboomvp.home.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hhit.bamboolibrary.base.BaseActivity;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.mvp.IView;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.di.module.MainModule;

public class MainActivity extends BaseActivity implements HomeContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new MainModule(this))
                .build()
                .inject(this);
    }
}

package com.hhit.bamboomvp.detail.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hhit.bamboolibrary.base.BaseActivity;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboomvp.R;

public class HHDetailActivity extends BaseActivity {

    private HHDetailFragment mHHdDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhdetail);

        mHHdDetailFragment = new HHDetailFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_container, mHHdDetailFragment);
        transaction.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hhdetail;
    }

    @Override
    protected void initialized() {

    }
}

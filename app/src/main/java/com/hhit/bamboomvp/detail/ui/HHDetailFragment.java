package com.hhit.bamboomvp.detail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhit.bamboolibrary.base.BaseFragment;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboomvp.R;

public class HHDetailFragment extends BaseFragment {

    @Override
    protected View onViewCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hhdetail, container, false);
    }

    @Override
    protected void onCreatedView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initialized() {

    }

    @Override
    protected void injectComponent(AppComponent appComponent) {

    }
}

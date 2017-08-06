package com.hhit.bamboomvp.home.presenter;

import com.hhit.bamboolibrary.di.scope.ActivityScope;
import com.hhit.bamboolibrary.mvp.BasePresenter;
import com.hhit.bamboomvp.home.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 2017/8/6.
 */

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {

    /**
     * MainModule 负责注入 Model 和 View 的实例给Presenter
     *
     * @param model
     * @param rootView
     */
    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }
}

package com.hhit.bamboomvp.home.di.module;

import com.hhit.bamboolibrary.di.scope.ActivityScope;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.model.HomeModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/8/6.
 */

@Module
public class MainModule {
    private HomeContract.View mView;

    /**
     * MainModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public MainModule(HomeContract.View view) {
        this.mView = view;
    }

    @ActivityScope
    @Provides
    HomeContract.View provideHomeView() { //注入View
        return this.mView;
    }

    @ActivityScope
    @Provides
    HomeContract.Model provideHomeModel(HomeModel model) { // 注入Model, Model实现类构造函数添加加 @Inject 注解
        return model;
    }
}

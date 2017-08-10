package com.hhit.bamboomvp.home.di.component;

import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.di.scope.ActivityScope;
import com.hhit.bamboomvp.home.di.module.MainModule;
import com.hhit.bamboomvp.home.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/8/6.
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}

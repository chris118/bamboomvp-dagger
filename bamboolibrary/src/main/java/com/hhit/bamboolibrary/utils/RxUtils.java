package com.hhit.bamboolibrary.utils;

import com.hhit.bamboolibrary.mvp.IView;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by xiaopeng on 2017/8/10.
 */

public class RxUtils {
    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {
        if (view instanceof LifecycleProvider){
            return ((LifecycleProvider)view).bindToLifecycle();
        }else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }

    }
}

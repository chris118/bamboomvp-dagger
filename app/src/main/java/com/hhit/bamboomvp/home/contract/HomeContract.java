package com.hhit.bamboomvp.home.contract;

import com.hhit.bamboolibrary.mvp.IModel;
import com.hhit.bamboolibrary.mvp.IView;
import com.hhit.bamboomvp.home.model.entity.User;
import com.hhit.bamboomvp.home.ui.adapter.UserAdapter;

import java.util.List;

import rx.Observable;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public interface HomeContract {
    interface View extends IView {
        void setAdapter(UserAdapter adapter);
        void showLoadMore();
        void hideLoadMore();
    }

    interface Model extends IModel {
        Observable<List<User>> getUsers(int lastIdQueried, int perPage);
    }
}

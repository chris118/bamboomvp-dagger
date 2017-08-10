package com.hhit.bamboomvp.home.presenter;

import com.hhit.bamboolibrary.di.scope.ActivityScope;
import com.hhit.bamboolibrary.mvp.BasePresenter;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.model.entity.User;
import com.hhit.bamboomvp.home.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaopeng on 2017/8/6.
 */

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    private static final int USERS_PER_PAGE = 10;
    private int mLastId = 1;
    private int preEndIndex;
    private UserAdapter mAdapter;
    private List<User> mUsers = new ArrayList<>();

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

    public void requestUsers(final Boolean bPullRefresh ){
        if (mAdapter == null) {
            mAdapter = new UserAdapter(mUsers);
            mRootView.setAdapter(mAdapter);//设置Adapter
        }

        if(bPullRefresh){
            mLastId = 1;
        }
        if(bPullRefresh) {
            mRootView.showLoading();
        } else {
            mRootView.showLoadMore();
        }
        mModel.getUsers(mLastId, USERS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                if(bPullRefresh) {
                    mRootView.hideLoading();
                } else {
                    mRootView.hideLoadMore();
                }
                mRootView.showMessage("加载错误");
            }

            @Override
            public void onNext(List<User> users) {
                mLastId = users.get(users.size() - 1).getId();//记录最后一个id,用于下一次请求
                preEndIndex = mUsers.size();//更新之前列表总长度,用于确定加载更多的起始位置

                if(bPullRefresh){
                    mUsers.clear();
                    mUsers.addAll(users);
                    mAdapter.notifyDataSetChanged();
                    mRootView.hideLoading();
                }else {
                    mAdapter.notifyItemRemoved(preEndIndex);//删除footer
                    mUsers.addAll(users); //更新数据源
                    mAdapter.notifyItemRangeInserted(preEndIndex, users.size());//刷新
                    mRootView.hideLoadMore();
                }
            }
        });
    }
}

package com.hhit.bamboomvp.home.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.hhit.bamboolibrary.base.BaseActivity;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.widget.RWLoadingDialog;
import com.hhit.bamboolibrary.widget.RWToast;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.di.component.DaggerMainComponent;
import com.hhit.bamboomvp.home.di.module.MainModule;
import com.hhit.bamboomvp.home.presenter.HomePresenter;
import com.hhit.bamboomvp.home.ui.adapter.PersonAdapter;
import com.hhit.bamboomvp.home.ui.adapter.UserAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    private final String TAG = this.getClass().getSimpleName();
    private RWLoadingDialog mRWLoadingDialog = null;
    private PersonAdapter mAdapter = null;
    private Boolean mLoadingMore = false;
    private RefreshLayout mRefreshLayout = null;

//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                RWToast.showText(this, "mvp演示程序");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void initialized() {
        /**
         * AppComponent 注入全局实例
         * MainComponent 注入HomeContract.View & HomeContract.Model 实例
         * HomePresenter 由 BaseActivity 注入
         * @param appComponent
         */
        DaggerMainComponent
                .builder()
                .appComponent(mAppComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        // init UI
        mRWLoadingDialog = new RWLoadingDialog(this);
        initRecycleView();

        //request users
        mPresenter.requestUsers(true);
    }

    @Override
    public void showLoading() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        mRWLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
//        mRWLoadingDialog.dismiss();
//        RWToast.showText(this, "刷新成功", true);
//        mSwipeRefreshLayout.setRefreshing(false);

        mRefreshLayout.finishRefresh();
        mRefreshLayout.setLoadmoreFinished(false);
    }

    @Override
    public void showMessage(String message) {
//        RWToast.showText(this, message, false);
    }

    @Override
    public void showLoadMore() {
        mLoadingMore = true;
    }

    @Override
    public void hideLoadMore() {
        mLoadingMore = false;

        mRefreshLayout.finishLoadmore();
        if (mAdapter.getItemCount() > 60) {
            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
            mRefreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
        }
    }

    @Override
    public void setAdapter(PersonAdapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
    }

    private void initRecycleView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                mPresenter.requestUsers(true);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                mPresenter.requestUsers(false);
            }
        });

        //触发自动刷新
        mRefreshLayout.autoRefresh();





//        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestUsers(true));

        //配合UserAdapter可以实现上拉加载更多
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
//                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
//                    if(!mLoadingMore){ //不要重复请求
//                        mPresenter.requestUsers(false);
//                    }
//                }
//
//            }
//        });
    }
}

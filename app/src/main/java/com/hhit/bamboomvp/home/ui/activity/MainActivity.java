package com.hhit.bamboomvp.home.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hhit.bamboolibrary.base.BaseActivity;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.widget.RWLoadingDialog;
import com.hhit.bamboolibrary.widget.RWToast;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.di.component.DaggerMainComponent;
import com.hhit.bamboomvp.home.di.module.MainModule;
import com.hhit.bamboomvp.home.presenter.HomePresenter;
import com.hhit.bamboomvp.home.ui.adapter.UserAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    private final String TAG = this.getClass().getSimpleName();
    private RWLoadingDialog mRWLoadingDialog = null;
    private GridLayoutManager mLayoutManager = null;
    private UserAdapter mAdapter = null;
    private Boolean mLoadingMore = false;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

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
                RWToast.showText(this, "刷新成功", true);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void initialized() {
        // init UI
        mRWLoadingDialog = new RWLoadingDialog(this);
        initRecycleView();

        //request users
        mPresenter.requestUsers(true);
    }

    /**
     * HomeContract.View & HomeContract.Model 注入
     * HomePresenter 由 BaseActivity 注入
     * @param appComponent
     */
    @Override
    protected void injectComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        Log.d(TAG, mPresenter.toString());
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
//        mRWLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
//        mRWLoadingDialog.dismiss();
        RWToast.showText(this, "刷新成功", true);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        RWToast.showText(this, message, false);
    }

    @Override
    public void showLoadMore() {
        mLoadingMore = true;
    }

    @Override
    public void hideLoadMore() {
        mLoadingMore = false;
    }

    @Override
    public void setAdapter(UserAdapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
    }

    private void initRecycleView() {
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestUsers(true);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                Log.d(TAG, "lastVisibleItemPosition" + String.valueOf(lastVisibleItemPosition));
                Log.d(TAG, "getItemCount" + String.valueOf(mAdapter.getItemCount()));
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
//                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
//                    if (isRefreshing) {
//                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
//                        return;
//                    }

                    if(!mLoadingMore){ //不要重复请求
                        mPresenter.requestUsers(false);
                    }
                }

            }
        });
    }
}

package com.hhit.bamboomvp.home.ui.adapter;

import android.view.View;

import com.hhit.bamboolibrary.base.BaseAdapter;
import com.hhit.bamboolibrary.base.BaseHolder;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.model.entity.User;
import com.hhit.bamboomvp.home.ui.holder.PersonHolder;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/22.
 */

public class PersonAdapter extends BaseAdapter<User> {

    public PersonAdapter(List<User> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<User> getHolder(View v, int viewType) {
        return new PersonHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.user_item;
    }
}

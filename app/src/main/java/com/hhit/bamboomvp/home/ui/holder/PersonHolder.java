package com.hhit.bamboomvp.home.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhit.bamboolibrary.base.BaseHolder;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.model.entity.User;

/**
 * Created by xiaopeng on 2017/8/22.
 */

public class PersonHolder extends BaseHolder<User> {
    private ImageView m_iv_avarta;
    private TextView m_tv_name;
    private Context mContext;

    public PersonHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext().getApplicationContext();
        m_iv_avarta = (ImageView) itemView.findViewById(R.id.iv_avatar);
        m_tv_name = (TextView) itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void setData(User data, int position) {
        Glide.with(mContext).load(data.getAvatarUrl()).into(m_iv_avarta);
        m_tv_name.setText(data.getLogin());
    }

    @Override
    protected void onRelease() {

    }
}

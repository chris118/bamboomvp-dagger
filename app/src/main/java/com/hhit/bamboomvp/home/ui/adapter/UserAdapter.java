package com.hhit.bamboomvp.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhit.bamboomvp.R;
import com.hhit.bamboomvp.home.model.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaopeng on 2017/8/9.
 */

public class UserAdapter extends RecyclerView.Adapter {
    private final static String TAG = UserAdapter.class.getSimpleName();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<User> mUsers;
    private Context mContext;

    public UserAdapter(List<User> users){
        mUsers = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        if (viewType == TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            UserViewHolder holder = new UserViewHolder(inflater.inflate(R.layout.user_item, parent, false));
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            UserFooterViewHolder holder = new UserFooterViewHolder(inflater.inflate(R.layout.user_footer_item, parent, false));
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mUsers.size() == 0){
            return;
        }
        if (holder instanceof UserViewHolder) {
            User user = mUsers.get(position);
            UserViewHolder userViewHolder = (UserViewHolder)holder;
            Glide.with(mContext).load(user.getAvatarUrl()).into(userViewHolder.m_iv_avarta);
            userViewHolder.m_tv_name.setText(user.getLogin());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size() == 0 ? 0 : mUsers.size() + 1;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        public  ImageView m_iv_avarta;

        @BindView(R.id.tv_name)
        public TextView m_tv_name;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class UserFooterViewHolder extends RecyclerView.ViewHolder {
        public UserFooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}

package com.hhit.bamboomvp.home.model.entity;

/**
 * Created by xiaopeng on 2017/8/8.
 */

public class User {
    private final int id;
    private final String login;
    private final String avatar_url;

    public User(int id, String login, String avatar_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getAvatarUrl() {
        if (avatar_url.isEmpty()) return avatar_url;
        return avatar_url.split("\\?")[0];
    }


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}

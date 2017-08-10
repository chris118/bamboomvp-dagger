package com.hhit.bamboomvp.home.model.api;

import com.hhit.bamboomvp.home.model.entity.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xiaopeng on 2017/8/8.
 */

public interface HomeService {
    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);
}

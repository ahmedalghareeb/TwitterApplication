package com.ghareeb.twitterapplication;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session){
        super(session);

    }

    public GetUsersApiCustomService getUserApiCustomService(){
        return getService(GetUsersApiCustomService.class);
    }

    interface GetUsersApiCustomService{
        @GET("/1.1/users/show.json")
        Call<User> show(@Query("user_id") long userId);

        @GET("/1.1/followers/list.json")
        Call<TwitterFollowerList> getFollowers(@Query("user_id") long userId);
    }
}

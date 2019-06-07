package com.ghareeb.twitterapplication;

import com.twitter.sdk.android.core.models.User;

import java.util.List;

public class TwitterFollowerList {

    private List<User> users;
    private String next_cursor;
    private String previous_cursor;
    private String next_cursor_str;
    private String previous_cursor_str;

    public TwitterFollowerList(List<User> users, String next_cursor, String previous_cursor, String next_cursor_str, String previous_cursor_str) {
        this.users = users;
        this.next_cursor = next_cursor;
        this.previous_cursor = previous_cursor;
        this.next_cursor_str = next_cursor_str;
        this.previous_cursor_str = previous_cursor_str;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getNext_cursor() {
        return next_cursor;
    }

    public String getPrevious_cursor() {
        return previous_cursor;
    }

    public String getNext_cursor_str() {
        return next_cursor_str;
    }

    public String getPrevious_cursor_str() {
        return previous_cursor_str;
    }


}

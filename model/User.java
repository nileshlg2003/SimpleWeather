package com.redorigami.simpleweather.model;

/**
 * Created by nilesh on 2/16/2015.
 */
public class User {
    public long userId;
    public String username;
    public String password;

    public User(long userId, String username, String password){
        this.userId=userId;
        this.username=username;
        this.password=password;
    }
}

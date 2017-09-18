package com.steven.user.domain;

/**
 * 用户DO
 * Created by lanhaozhi on 2017/8/17.
 */
public class User {
    public final static String UK = "_user" ;
    private String id ;
    private String userName ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

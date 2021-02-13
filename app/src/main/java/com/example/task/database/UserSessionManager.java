package com.example.task.database;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private static final String PREFER_NAME = "TASK";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    private static final String USER_TOKEN = "UserToken";
    private static final String USER_ID = "UserId";

    public UserSessionManager(Context context){
        pref = context.getSharedPreferences(PREFER_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String userId){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(USER_ID,userId);
        editor.commit();
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN,false);
    }

    public String getUserId(){
        return pref.getString(USER_ID,null);
    }

    public void logoutUser(){
        editor.putBoolean(IS_USER_LOGIN,false);
        editor.commit();
    }



}

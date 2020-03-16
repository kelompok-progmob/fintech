package com.tyga.fintech.api;

import android.content.SharedPreferences;

import com.tyga.fintech.model.UserWithToken;

public class TokenManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(UserWithToken token){
        editor.putString("ACCESS_TOKEN", token.getToken()).commit();
        editor.putString("REFRESH_TOKEN", token.getToken()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public UserWithToken getToken(){
        UserWithToken token = new UserWithToken();
        token.setToken(prefs.getString("ACCESS_TOKEN", null));
//        token.setRefreshToken(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }
}

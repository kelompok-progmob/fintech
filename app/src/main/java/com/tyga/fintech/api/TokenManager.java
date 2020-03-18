package com.tyga.fintech.api;

import android.content.SharedPreferences;

import com.tyga.fintech.model.Merchant;
import com.tyga.fintech.model.Nasabah;
import com.tyga.fintech.model.User;
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
        editor.apply();
    }

    public void saveUser(UserWithToken token){
        editor.putString("ID_USER", String.valueOf(token.getUser().getIDUser())).commit();
        editor.putString("NO_HP", String.valueOf(token.getUser().getNoHP())).commit();
        editor.putString("PASSWORD", String.valueOf(token.getUser().getPassword())).commit();
        editor.putString("ROLE", String.valueOf(token.getUser().getRole())).commit();
        editor.apply();
    }

    public void saveNasabah(UserWithToken token){

        editor.putString("ID_NASABAH", String.valueOf(token.getNasabah().getIDNasabah())).commit();
        editor.putString("NAMA", String.valueOf(token.getNasabah().getNama())).commit();
        editor.putString("NIK", String.valueOf(token.getNasabah().getNik())).commit();
        editor.apply();
    }

    public void saveMerchant(UserWithToken token){
        editor.putString("ID_MERCHANT", String.valueOf(token.getMerchant().getId_merchant())).commit();
        editor.putString("IMAGE", String.valueOf(token.getMerchant().getImage())).commit();
        editor.putString("NAMA", String.valueOf(token.getMerchant().getNama())).commit();
        editor.putString("ALAMAT", String.valueOf(token.getMerchant().getAlamat())).commit();
        editor.putString("DESKRIPSI", String.valueOf(token.getMerchant().getDeskripsi())).commit();
        editor.apply();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
        editor.remove("NO_HP").commit();
    }

    public UserWithToken getToken(){
        UserWithToken token = new UserWithToken();
        token.setToken(prefs.getString("ACCESS_TOKEN", null));
//        token.setRefreshToken(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }

    public UserWithToken getUser(){
        UserWithToken token = new UserWithToken();
        User user = new User();
        if (prefs.getString("ID_USER",null) != null){
            user.setIDUser(Long.parseLong(prefs.getString("ID_USER",null)));
        }
        else{
            user.setIDUser(0);
        }

        user.setNoHP(prefs.getString("NO_HP",null));
        user.setPassword(prefs.getString("PASSWORD",null));
        user.setRole(prefs.getString("ROLE",null));

        token.setUser(user);
        return token;
    }

    public UserWithToken getNasabah(){
        UserWithToken token = new UserWithToken();
        Nasabah user = new Nasabah();
        if (prefs.getString("ID_NASABAH",null) != null){
            user.setIDNasabah(Long.parseLong(prefs.getString("ID_NASABAH",null)));
        }
        else{
            user.setIDNasabah(0);
        }

        user.setNama(prefs.getString("NAMA",null));
        user.setNik(prefs.getString("NIK",null));


        token.setNasabah(user);
        return token;
    }

    public UserWithToken getMerchant(){
        UserWithToken token = new UserWithToken();
        Merchant user = new Merchant();
        user.setId_merchant(prefs.getString("ID_MERCHANT",null));
        user.setNama(prefs.getString("NAMA",null));
        user.setImage(prefs.getString("IMAGE",null));
        user.setAlamat(prefs.getString("ALAMAT",null));
        user.setDeskripsi(prefs.getString("DESKRIPSI",null));


        token.setMerchant(user);
        return token;
    }
}

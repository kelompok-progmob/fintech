// UserWithToken.java

package com.tyga.fintech.model;

import java.util.*;

public class UserWithToken {
    private Nasabah nasabah;
    private Merchant merchant;
    private String token;
    private User user;

    public Nasabah getNasabah() { return nasabah; }
    public void setNasabah(Nasabah value) { this.nasabah = value; }

    public String getToken() { return token; }
    public void setToken(String value) { this.token = value; }

    public User getUser() { return user; }
    public void setUser(User value) { this.user = value; }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

}

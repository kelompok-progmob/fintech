package com.tyga.fintech.api;

import com.tyga.fintech.model.UserWithToken;
import com.tyga.fintech.model.Merchant;
import com.tyga.fintech.model.Promo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("get-merchant-per-lpd")
    Call<List<Merchant>> getMerchant(@Query("id_lpd") String idLpd);

    @GET("get-promo-per-merchant")
    Call<List<Promo>> getPromo(@Query("id_lpd") String idLpd);

    @POST("refresh")
    @FormUrlEncoded
    Call<UserWithToken> refresh(@Field("refresh_token") String refreshToken);

    @POST("login")
    @FormUrlEncoded
    Call<UserWithToken> login(@Field("no_hp") String noHp, @Field("password") String password);

}

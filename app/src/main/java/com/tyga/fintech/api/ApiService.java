package com.tyga.fintech.api;

import com.tyga.fintech.model.Lpd;
import com.tyga.fintech.model.MappingUser;
import com.tyga.fintech.model.Nasabah;
import com.tyga.fintech.model.ResponseMessage;
import com.tyga.fintech.model.UserWithToken;
import com.tyga.fintech.model.Merchant;
import com.tyga.fintech.model.Promo;
import com.tyga.fintech.model.Transaksi;
import com.tyga.fintech.model.ValidasiTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("get-merchant-per-lpd")
    Call<List<Merchant>> getMerchant();

    @GET("get-promo-per-merchant")
    Call<List<Promo>> getPromo();

    @GET("get-history-nasabah")
    Call<List<Transaksi>> getHistoryNasabah();

    @GET("get-history-merchant")
    Call<List<Transaksi>> getHistoryMerchant();

    @GET("get-lpd-merchant")
    Call<List<Lpd>> getLpdMerchant();

    @GET("get-lpd-nasabah")
    Call<List<Lpd>> getLpdNasabah();

    @GET("get-saldo-merchant")
    Call<List<Merchant>> getSaldoMerchant();

    @GET("get-saldo-nasabah")
    Call<List<MappingUser>> getSaldoNasabah();

    @GET("check-phone")
    Call<ResponseMessage> checkPhone(@Query("no_hp") String no_hp);

    @GET("all-lpd")
    Call<List<Lpd>> allLpd();

    @FormUrlEncoded
    @POST("insert-transaksi")
    Call<ResponseMessage> insertTransaksi(@Field("nominal") String nominal,
                                          @Field("id_merchant") String idMerchant);

    @FormUrlEncoded
    @POST("topup-saldo")
    Call<ResponseMessage> TopupSaldo(@Field("nominal") String nominal);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseMessage> register(@Field("nik") String nik,
                                   @Field("nama") String nama,
                                   @Field("no_hp") String no_hp,
                                   @Field("password") String password,
                                   @Field("id_lpd") String lpd,
                                   @Field("role") String role
                                   );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseMessage> registerMerchant(@Field("alamat") String alamat,
                                   @Field("nama") String nama,
                                   @Field("no_hp") String no_hp,
                                   @Field("password") String password,
                                   @Field("id_lpd") String lpd,
                                   @Field("role") String role
    );

    @POST("refresh")
    @FormUrlEncoded
    Call<UserWithToken> refresh(@Field("refresh_token") String refreshToken);

    @POST("login-user")
    @FormUrlEncoded
    Call<UserWithToken> login(@Field("no_hp") String noHp, @Field("password") String password);

    @GET("get-pendapatan-today")
    Call<Double> getPendapatanToday();

    @GET("get-transaksi-today")
    Call<Integer> getTransaksiToday();

    @GET("get-points")
    Call<Integer> getPoints();

    @FormUrlEncoded
    @POST("get-validasi-transaksi")
    Call<ValidasiTransaksi> getValidasiTransaksi(@Field("id_merchant") String idMerchant, @Field("nominal") String nominal);

}

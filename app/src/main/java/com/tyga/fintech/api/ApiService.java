package com.tyga.fintech.api;

import com.tyga.fintech.model.Merchant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("get-merchant-per-lpd")
    Call<List<Merchant>> getMerchant(@Query("id_lpd") String idLpd);

}

package com.tyga.fintech.api;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.tyga.fintech.BuildConfig;
import com.tyga.fintech.LoginActivity;
import com.tyga.fintech.MerchantActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ApiClient {
    private static Retrofit retrofitNoPassport = null;
    public static Retrofit getClient() {
        if (retrofitNoPassport==null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();
            retrofitNoPassport = new Retrofit.Builder()
                    .baseUrl("https://fintechapis.herokuapp.com/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitNoPassport;
    }

    private static final String BASE_URL = "https://fintechapis.herokuapp.com/api/";

    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = getClient(client);

    private static OkHttpClient buildClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Connection", "close");

                        request = builder.build();

                        return chain.proceed(request);

                    }
                });

//        if(BuildConfig.DEBUG){
//            builder.addNetworkInterceptor(new StethoInterceptor());
//        }

        return builder.build();

    }

    private static Retrofit getClient(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service){
        return retrofit.create(service);
    }

    public static <T> T createServiceWithAuth(Class<T> service, TokenManager tokenManagerParam, Context context){

        tokenManagerParam = TokenManager.getInstance(context.getSharedPreferences("prefs", MODE_PRIVATE));
        final TokenManager tokenManager = tokenManagerParam;

        if(tokenManager.getToken() == null){
            context.startActivity(new Intent(context, LoginActivity.class));
            ((AppCompatActivity)context).finish();
        }

        OkHttpClient newClient = client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder builder = request.newBuilder();

                if(tokenManager.getToken().getToken() != null){
                    builder.addHeader("Authorization", "Bearer " + tokenManager.getToken().getToken());
                }
                request = builder.build();
                return chain.proceed(request);
            }
        }).authenticator(CustomAuthenticator.getInstance(tokenManager)).build();

        Retrofit newRetrofit = retrofit.newBuilder().client(newClient).build();
        return newRetrofit.create(service);

    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
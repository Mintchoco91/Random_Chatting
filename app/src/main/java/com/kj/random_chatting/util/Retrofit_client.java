package com.kj.random_chatting.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kj.random_chatting.common.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit_client {
    private static Context context;

    public static Retrofit_interface getApiService(Context mContext){
        context = mContext;
        return getInstance().create(Retrofit_interface.class);
    }

    private static Retrofit getInstance(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthInterceptor(context)).build();

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(Constants.AWS_PHP_SERVER_IP_ADDRESS)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}

package com.xbc.douban.api;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class RetrofitManager {
    private static final RetrofitManager ourInstance = new RetrofitManager();

    private MovieService movieService;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(120,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public MovieService getMovieService() {
        if (movieService == null) {
            movieService = retrofit.create(MovieService.class);
        }
        return movieService;
    }

}

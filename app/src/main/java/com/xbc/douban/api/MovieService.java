package com.xbc.douban.api;

import com.xbc.douban.movie.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xiaobocui on 2017/7/14.
 */

public interface MovieService {

    @FormUrlEncoded
    @POST("movie/in_theaters")
    Call<MovieResponse> getInTheaters(@Field("start") int start, @Field("count") int count);

    @GET("movie/coming_soon")
    Call<MovieResponse> getComingSoon(@Query("start") int start, @Query("count") int count);

    @GET("movie/coming_soon")
    Call<MovieResponse> getComingSoon2(@Query("start") int start, @Query("count") int count);

    @GET("movie/coming_soon")
    Observable<MovieResponse> getComingSoon3(@Query("start") int start, @Query("count") int count);
}

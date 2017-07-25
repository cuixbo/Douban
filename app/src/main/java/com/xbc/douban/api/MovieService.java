package com.xbc.douban.api;

import com.xbc.douban.movie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xiaobocui on 2017/7/14.
 */

public interface MovieService {

    @GET("movie/in_theaters")
    Call<MovieResponse> getInTheaters(@Query("start") int start, @Query("count") int count);


}
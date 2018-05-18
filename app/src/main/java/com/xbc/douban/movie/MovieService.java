package com.xbc.douban.movie;

import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.movie.model.SubjectsBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @FormUrlEncoded
    @POST("movie/in_theaters")
    Call<MovieResponse> getInTheaters(@Field("start") int start, @Field("count") int count);

    @GET("movie/coming_soon")
    Call<MovieResponse> getComingSoon(@Query("start") int start, @Query("count") int count);

    @GET("movie/search")
    Call<MovieResponse> searchMovie(@Query("q") String content,@Query("start") int start, @Query("count") int count);

    @GET("movie/subject/{id}")
    Call<SubjectsBean> getMovieSubject(@Path("id") String id);

    @GET("movie/subject/{id}/comments")
    Call<SubjectsBean> getMovieComments(@Path("id") String id, @Query("start") int start, @Query("count") int count);
}

package com.xbc.douban.movie.model;

import com.xbc.douban.api.RetrofitManager;
import com.xbc.douban.base.BaseModel;

import retrofit2.Callback;

/**
 * Created by xiaobocui on 2017/7/21.
 */

public class MovieModel extends BaseModel {
    private static final MovieModel ourInstance = new MovieModel();

    public static MovieModel getInstance() {
        return ourInstance;
    }

    private MovieModel() {

    }

    public void getHotMovies(int start,int count,Callback<MovieResponse> callback){
        RetrofitManager.getInstance()
                .getMovieService()
                .getInTheaters(start,count)
                .enqueue(callback);
    }
    public void getComingSoonMovies(int start,int count,Callback<MovieResponse> callback){
        RetrofitManager.getInstance()
                .getMovieService()
                .getComingSoon(start,count)
                .enqueue(callback);
    }

}

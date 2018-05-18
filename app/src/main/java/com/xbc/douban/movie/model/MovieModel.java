package com.xbc.douban.movie.model;

import com.xbc.douban.api.RetrofitManager;
import com.xbc.douban.base.BaseModel;

import retrofit2.Callback;

public class MovieModel extends BaseModel {
    private static final MovieModel ourInstance = new MovieModel();

    public static MovieModel getInstance() {
        return ourInstance;
    }

    private MovieModel() {

    }

    public void getHotMovies(int start, int count, Callback<MovieResponse> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .getInTheaters(start, count)
                .enqueue(callback);
    }

    public void getComingSoonMovies(int start, int count, Callback<MovieResponse> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .getComingSoon(start, count)
                .enqueue(callback);
    }

    public void getMovieSubject(String id, Callback<SubjectsBean> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .getMovieSubject(id)
                .enqueue(callback);
    }

    public void searchMovie(String content, Callback<MovieResponse> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .searchMovie(content, 0, 20)
                .enqueue(callback);
    }
}

package com.xbc.douban.movie.model;

import com.xbc.douban.api.ApiCallback;
import com.xbc.douban.api.RetrofitManager;
import com.xbc.douban.base.BaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public void getComingSoonMovies2(int start, int count, ApiCallback<MovieResponse> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .getComingSoon(start, count)
                .enqueue(callback);
    }

    public void getComingSoonMovies3(int start, int count, final ApiCallback<MovieResponse> callback) {
        RetrofitManager.getInstance().getMovieService()
                .getComingSoon3(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public void getMovieSubject(String id, Callback<SubjectsBean> callback) {
        RetrofitManager.getInstance()
                .getMovieService()
                .getMovieSubject(id)
                .enqueue(callback);
    }
}

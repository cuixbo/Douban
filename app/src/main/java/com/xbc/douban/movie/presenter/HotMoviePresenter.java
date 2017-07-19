package com.xbc.douban.movie.presenter;

import com.xbc.douban.api.RetrofitManager;
import com.xbc.douban.movie.contract.HotMovieContract;
import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaobocui on 2017/7/18.
 */

public class HotMoviePresenter implements HotMovieContract.Presenter {

    HotMovieContract.View mHotMovieView;

    public HotMoviePresenter(HotMovieContract.View mHotMovieView) {
        this.mHotMovieView = mHotMovieView;
        this.mHotMovieView.setPresenter(this);
    }

    @Override
    public void start() {

        getHotMovies();
    }

    @Override
    public void getHotMovies() {
        RetrofitManager.getInstance()
                .getMovieService()
                .getInTheaters()
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        mHotMovieView.notifyDataSetChanged(response.body().subjects);
                        Log.log("onResponse");
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.log("onFailure:" + t.getLocalizedMessage());
                    }
                });
    }
}

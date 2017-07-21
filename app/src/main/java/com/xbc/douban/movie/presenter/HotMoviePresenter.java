package com.xbc.douban.movie.presenter;

import com.xbc.douban.movie.contract.HotMovieContract;
import com.xbc.douban.movie.model.MovieModel;
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
    MovieModel mMovieModel = MovieModel.getInstance();

    public HotMoviePresenter(HotMovieContract.View mHotMovieView) {
        this.mHotMovieView = mHotMovieView;
        this.mHotMovieView.setPresenter(this);
    }

    @Override
    public void start() {
        mHotMovieView.setRefresh(true);
        getHotMovies();
    }

    @Override
    public void getHotMovies() {
        mMovieModel.getHotMovies(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mHotMovieView.notifyDataSetChanged(response.body().subjects);
                Log.log("onResponse");
                mHotMovieView.setRefresh(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.log("onFailure:" + t.getLocalizedMessage());
                mHotMovieView.setRefresh(false);
            }
        });
    }
}

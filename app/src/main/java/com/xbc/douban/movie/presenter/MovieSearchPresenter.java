package com.xbc.douban.movie.presenter;

import android.text.TextUtils;

import com.xbc.douban.movie.contract.MovieSearchContract;
import com.xbc.douban.movie.model.MovieModel;
import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchPresenter implements MovieSearchContract.Presenter {

    MovieSearchContract.View mHotMovieView;
    MovieModel mMovieModel = MovieModel.getInstance();

    public MovieSearchPresenter(MovieSearchContract.View mHotMovieView) {
        this.mHotMovieView = mHotMovieView;
        this.mHotMovieView.setPresenter(this);
    }

    @Override
    public void start() {
//        mHotMovieView.setRefresh(true);
//        searchMovie();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void searchMovie(String content) {
        if (TextUtils.isEmpty(content)) {
            mHotMovieView.setRefresh(false);
            return;
        }
        mMovieModel.searchMovie(content, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse:" + response.code());
                MovieResponse resp = response.body();
                mHotMovieView.setRefresh(false);
                if (resp != null) {
                    mHotMovieView.notifyDataSetChanged(resp.subjects, false);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.log("onFailure:" + t.getLocalizedMessage());
                mHotMovieView.setRefresh(false);
            }
        });
    }

}

package com.xbc.douban.movie.presenter;

import com.xbc.douban.movie.contract.HotMovieContract;
import com.xbc.douban.movie.model.MovieModel;
import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.util.Log;
import com.xbc.douban.widget.LoadMoreScrollListener;

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
    public void destroy() {

    }

    @Override
    public void getHotMovies() {
        mMovieModel.getHotMovies(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse");
                mHotMovieView.setRefresh(false);
                mHotMovieView.notifyDataSetChanged(response.body().subjects, false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.log("onFailure:" + t.getLocalizedMessage());
                mHotMovieView.setRefresh(false);
            }
        });
    }

    @Override
    public void getHotMoviesMore() {
        mMovieModel.getHotMovies(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse");
//                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_FAILED);
//                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_NO_MORE);
                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_SUCCESS);
                mHotMovieView.notifyDataSetChanged(response.body().subjects, true);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.log("onFailure:" + t.getLocalizedMessage());
                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_FAILED);
            }
        });
    }
}

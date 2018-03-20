package com.xbc.douban.movie.presenter;

import com.xbc.douban.movie.contract.InTheaterMovieContract;
import com.xbc.douban.movie.model.MovieModel;
import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.util.Log;
import com.xbc.douban.widget.loadmore.LoadMoreScrollListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaobocui on 2017/7/18.
 */

public class InTheaterMoviePresenter implements InTheaterMovieContract.Presenter {

    InTheaterMovieContract.View mHotMovieView;
    MovieModel mMovieModel = MovieModel.getInstance();

    public InTheaterMoviePresenter(InTheaterMovieContract.View mHotMovieView) {
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
        mMovieModel.getHotMovies(0, 10, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse");
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

    @Override
    public void getHotMoviesMore(int start) {
        mMovieModel.getHotMovies(start, 10, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse");
                MovieResponse resp = response.body();
                if (resp != null) {
                    if (resp.subjects == null || resp.subjects.isEmpty()) {
                        mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_NO_MORE);
                    } else {
                        mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_SUCCESS);
                        mHotMovieView.notifyDataSetChanged(resp.subjects, true);
                    }
                } else {
                    mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_NO_MORE);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.log("onFailure:" + t.getLocalizedMessage());
                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_FAILED);
            }
        });
    }

}

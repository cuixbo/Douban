package com.xbc.douban.movie.presenter;

import com.xbc.douban.api.ApiCallback;
import com.xbc.douban.movie.contract.ComingSoonMovieContract;
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

public class ComingSoonMoviePresenter implements ComingSoonMovieContract.Presenter {

    ComingSoonMovieContract.View mHotMovieView;
    MovieModel mMovieModel = MovieModel.getInstance();

    public ComingSoonMoviePresenter(ComingSoonMovieContract.View mHotMovieView) {
        this.mHotMovieView = mHotMovieView;
        this.mHotMovieView.setPresenter(this);
    }

    @Override
    public void start() {
        mHotMovieView.setRefresh(true);
        getComingSoonMovies();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void getComingSoonMovies() {
//        mMovieModel.getComingSoonMovies(0,10,new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                Log.log("onResponse");
//                mHotMovieView.setRefresh(false);
//                mHotMovieView.notifyDataSetChanged(response.body().subjects, false);
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.log("onFailure:" + t.getLocalizedMessage());
//                mHotMovieView.setRefresh(false);
//            }
//        });
        getComingSoonMovies2();
    }

    @Override
    public void getComingSoonMovies2() {
        mMovieModel.getComingSoonMovies2(0, 10, new ApiCallback<MovieResponse>() {
            @Override
            public void onSuccess(MovieResponse movieResponse) {
                Log.log("ComingSoon.onSuccess");
                mHotMovieView.notifyDataSetChanged(movieResponse.subjects, false);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.log("ComingSoon.onFailed");
            }

            @Override
            public boolean onError(Throwable t,String msg) {
                Log.log("ComingSoon.onError:" + msg);
                return true;
            }

            @Override
            public void onCompleted() {
                mHotMovieView.setRefresh(false);
                Log.log("ComingSoon.onComplete");
            }
        });
    }

    @Override
    public void getComingSoonMoviesMore(int start) {
        mMovieModel.getComingSoonMovies(start, 10, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.log("onResponse");
                if (response.body().subjects == null || response.body().subjects.isEmpty()) {
                    mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_NO_MORE);
                } else {
                    mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_SUCCESS);
                    mHotMovieView.notifyDataSetChanged(response.body().subjects, true);
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

package com.xbc.douban.movie.presenter;

import com.xbc.douban.movie.contract.ComingSoonMovieContract;
import com.xbc.douban.movie.model.MovieModel;
import com.xbc.douban.movie.model.MovieResponse;
import com.xbc.douban.widget.loadmore.LoadMoreScrollListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mMovieModel.getComingSoonMovies(0, 10, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mHotMovieView.setRefresh(false);
                MovieResponse resp = response.body();
                mHotMovieView.setRefresh(false);
                if (resp != null) {
                    mHotMovieView.notifyDataSetChanged(resp.subjects, false);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mHotMovieView.setRefresh(false);
            }
        });
    }


    @Override
    public void getComingSoonMoviesMore(int start) {
        mMovieModel.getComingSoonMovies(start, 10, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
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
                mHotMovieView.setLoadMoreState(LoadMoreScrollListener.State.STATE_FAILED);
            }
        });
    }
}

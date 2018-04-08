package com.xbc.douban.movie.presenter;

import com.xbc.douban.movie.contract.MovieDetailContract;
import com.xbc.douban.movie.model.MovieModel;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.util.Log;
import com.xbc.douban.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    MovieDetailContract.View mHotMovieView;
    MovieModel mMovieModel = MovieModel.getInstance();

    public MovieDetailPresenter(MovieDetailContract.View mHotMovieView) {
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
        mMovieModel.getMovieSubject(mHotMovieView.getMovieId(), new Callback<SubjectsBean>() {
            @Override
            public void onResponse(Call<SubjectsBean> call, Response<SubjectsBean> response) {
                SubjectsBean subjectsBean = response.body();
                if (subjectsBean != null) {
                    Log.log("onResponse:" + subjectsBean.toString());
                    mHotMovieView.updateMovieSubject(subjectsBean);
                } else {
                    ToastUtil.show("抱歉,暂时还没有该影片的相关介绍");
                }
            }

            @Override
            public void onFailure(Call<SubjectsBean> call, Throwable t) {
                Log.log("onFailure1:" + t.getLocalizedMessage());
            }
        });
    }


}

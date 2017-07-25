package com.xbc.douban.movie.contract;

import com.xbc.douban.base.IBasePresenter;
import com.xbc.douban.base.IBaseView;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/18.
 */

public interface HotMovieContract {

    interface View extends IBaseView<Presenter> {
        void notifyDataSetChanged(List<SubjectsBean> subjects, boolean append);

        void setRefresh(boolean refresh);

        void setLoadMoreState(int state);
    }

    interface Presenter extends IBasePresenter {
        void getHotMovies();

        void getHotMoviesMore(int start);
    }
}

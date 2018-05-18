package com.xbc.douban.movie.contract;

import com.xbc.douban.base.IBasePresenter;
import com.xbc.douban.base.IBaseView;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.List;

public interface MovieSearchContract {

    interface View extends IBaseView<Presenter> {
        void notifyDataSetChanged(List<SubjectsBean> subjects, boolean append);

        void setRefresh(boolean refresh);
    }

    interface Presenter extends IBasePresenter {
        void searchMovie(String content);
    }
}

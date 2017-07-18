package com.xbc.douban.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.base.BaseFragment;
import com.xbc.douban.movie.adapter.MovieAdapter;
import com.xbc.douban.movie.contract.HotMovieContract;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.movie.presenter.HotMoviePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class HotMovieFragment extends BaseFragment implements HotMovieContract.View {

    private HotMovieContract.Presenter mPresenter;

    private RecyclerView recyclerView;
    private List<SubjectsBean> mData = new ArrayList<SubjectsBean>();
    private MovieAdapter mAdapter = new MovieAdapter(mData);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HotMoviePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_hot_movie, container, false);
    }

    @Override
    public void setPresenter(HotMovieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mPresenter.start();
    }

    private void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void notifyDataSetChanged(List<SubjectsBean> subjects) {
        mData.clear();
        mData.addAll(subjects);
        mAdapter.notifyDataSetChanged();
    }
}

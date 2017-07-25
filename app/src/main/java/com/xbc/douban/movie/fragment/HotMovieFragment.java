package com.xbc.douban.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.base.BaseFragment;
import com.xbc.douban.base.RecyclerViewHelper;
import com.xbc.douban.movie.adapter.MovieAdapter;
import com.xbc.douban.movie.contract.HotMovieContract;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.movie.presenter.HotMoviePresenter;
import com.xbc.douban.util.Log;
import com.xbc.douban.widget.LoadMoreScrollListener;
import com.xbc.douban.widget.OnLoadMoreListener;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class HotMovieFragment extends BaseFragment implements HotMovieContract.View {

    private HotMovieContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter = new MovieAdapter();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;

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
        initIntent();
        initView();
        initListener();
        mPresenter.start();
    }

    private void initIntent() {

    }

    private void initView() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_layout);

        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(
                new RecyclerViewHelper
                        .InsetDividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)
                        .setMargin(40, 40, 0, 0)
                        //.setHeaderDividersEnabled(false)
                        .setFooterDividersEnabled(false)
        );
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_main);
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefresh(true);
                mPresenter.getHotMovies();
                mAdapter.onStateChanged(LoadMoreScrollListener.State.STATE_DEFAULT);
            }
        });

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //处理加载更多的异步任务
                Log.log("onLoadMore()");
                mPresenter.getHotMoviesMore();
            }
        });

        mAdapter.setOnItemClickListener(new RecyclerViewHelper.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View item, int position) {
                log(position + "");
            }
        });
    }


    @Override
    public void notifyDataSetChanged(List<SubjectsBean> subjects, boolean append) {
        if (append) {
            mAdapter.getData().addAll(subjects);
        } else {
            mAdapter.getData().clear();
            mAdapter.getData().addAll(subjects);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setRefresh(boolean refresh) {
        mSwipeRefreshLayout.setRefreshing(refresh);
    }

    /**
     * 异步操作后的状态回传,这里是presenter传过来的
     */
    @Override
    public void setLoadMoreState(int state) {
        mAdapter.onStateChanged(state);
    }
}

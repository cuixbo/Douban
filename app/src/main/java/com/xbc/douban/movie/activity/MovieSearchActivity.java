package com.xbc.douban.movie.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.xbc.douban.R;
import com.xbc.douban.base.BaseActivity;
import com.xbc.douban.movie.adapter.MovieAdapter;
import com.xbc.douban.movie.contract.MovieSearchContract;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.movie.presenter.MovieSearchPresenter;
import com.xbc.douban.util.SoftInputHelper;
import com.xbc.douban.widget.loadmore.LoadMoreScrollListener;
import com.xbc.douban.widget.loadmore.RecyclerViewHelper;

import java.util.List;


public class MovieSearchActivity extends BaseActivity implements MovieSearchContract.View {

    private MovieSearchContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter = new MovieAdapter();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView mTvBack,mTvSearch;
    private EditText mEtContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        new MovieSearchPresenter(this);
        initIntent();
        initView();
        initListener();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MovieSearchContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTvSearch = (TextView) findViewById(R.id.tv_search);
        mEtContent = (EditText) findViewById(R.id.et_content);

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

    @Override
    protected void initListener() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftInputHelper.hideSoftInput(getContext(), mEtContent);
                setRefresh(true);
                mPresenter.searchMovie(getSearchContent());
                mAdapter.onStateChanged(LoadMoreScrollListener.State.STATE_NO_MORE);
            }
        });

        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mTvSearch.performClick();
                    return true;
                }
                return false;
            }
        });
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setOnRefreshListener(null);

        mAdapter.setOnItemClickListener(new RecyclerViewHelper.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View item, int position) {
                log(position + "");
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(Intent.EXTRA_UID, mAdapter.getData().get(position).id);
                startActivity(intent);
            }
        });
    }

    public String getSearchContent() {
        return mEtContent.getText().toString();
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


}

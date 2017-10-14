package com.xbc.douban.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by xiaobocui on 2017/7/24.
 */

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener{

    LinearLayoutManager mLinearLayoutManager;
    int totalItemCount;
    int lastVisibleItem;
    int firstVisibleItem;
    int mState;
    LoadMoreStateChangedListener mLoadMoreStateChangedListener;//这里应该是Adapter实现了LoadMoreStateChangedListener
    OnLoadMoreListener mOnLoadMoreListener;//OnLoadMore()触发的回调

    public static class State {
        public static final int STATE_DEFAULT = 0;//不显示  初始状态或数量太少
        public static final int STATE_LOADING = 1;//加载中  loading
        public static final int STATE_FAILED = 2;//加载失败  显示
        public static final int STATE_SUCCESS = 3;//加载成功 隐式状态
        public static final int STATE_NO_MORE = 4;//没有更多了 显示
    }

    public LoadMoreScrollListener(LoadMoreStateChangedListener adapter) {
        mLoadMoreStateChangedListener = adapter;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLinearLayoutManager == null) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                mLinearLayoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
            } else {
                return;
            }
        }
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        totalItemCount = mLinearLayoutManager.getItemCount();
        if (lastVisibleItem == totalItemCount - 1 && firstVisibleItem != 0) {
            //TODO在此处执行自己加载更多数据的异步
            if (mState == State.STATE_NO_MORE) {
                return;
            }
            if (mState != State.STATE_LOADING) {
                if (mOnLoadMoreListener != null) {
                    mOnLoadMoreListener.onLoadMore();
                }
                onStateChanged(State.STATE_LOADING);
            }
        }
    }

    public void setState(int state) {
        this.mState = state;
    }

    public void onStateChanged(int state) {
        setState(state);
        if (mLoadMoreStateChangedListener != null) {
            mLoadMoreStateChangedListener.onStateChanged(state);
        }
    }

    public void loadMore(){
        if (mState != State.STATE_LOADING) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMore();
            }
            onStateChanged(State.STATE_LOADING);
        }
    }
}
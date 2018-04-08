package com.xbc.douban.widget.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;
    private int totalItemCount;
    private int lastVisibleItem;
    private int firstVisibleItem;
    private int mState;
    private LoadMoreStateChangedListener mLoadMoreStateChangedListener;//这里应该是Adapter实现了LoadMoreStateChangedListener
    private OnLoadMoreListener mOnLoadMoreListener;//OnLoadMore()触发的回调

    public static class State {
        public static final int STATE_DEFAULT = 0;//不显示  初始状态或数量太少
        public static final int STATE_LOADING = 1;//加载中  loading
        public static final int STATE_FAILED = 2;//加载失败  显示
        public static final int STATE_SUCCESS = 3;//加载成功 隐式状态
        public static final int STATE_NO_MORE = 4;//没有更多了 显示

        public static final int VISIBLE = 99;//没有状态含义,只是显示footer
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
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
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
            if (mState == State.STATE_NO_MORE) {
                return;
            }
            if (mState != State.STATE_LOADING) {
                //由于初始状态是不可见的,当滑动到超出一屏时让其可见
                onStateChanged(State.VISIBLE);
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

    public void loadMore() {
        if (mState != State.STATE_LOADING) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMore();
            }
            onStateChanged(State.STATE_LOADING);
        }
    }
}
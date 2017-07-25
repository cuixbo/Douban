package com.xbc.douban.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbc.douban.R;
import com.xbc.douban.widget.LoadMoreScrollListener;
import com.xbc.douban.widget.LoadMoreStateChangedListener;
import com.xbc.douban.widget.OnLoadMoreListener;

/**
 * Created by xiaobocui on 2017/7/25.
 */

public class BaseLoadMoreRecyclerViewAdapter extends BaseRecyclerViewAdapter implements LoadMoreStateChangedListener {

    protected int mState = 0;
    protected LoadMoreScrollListener mScrollListener = new LoadMoreScrollListener(this);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View mFooterView = LayoutInflater.from(mContext).inflate(R.layout.item_movie_footer, parent, false);
            return new LoadMoreFooterViewHolder(mFooterView);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (holder instanceof LoadMoreFooterViewHolder) {
                bindLoadMoreFooterView(((LoadMoreFooterViewHolder) holder), position);
            }
            return;
        }
    }

    private void bindLoadMoreFooterView(LoadMoreFooterViewHolder holder, int position) {
        holder.itemView.setVisibility(View.VISIBLE);
        switch (mState) {
            case LoadMoreScrollListener.State.STATE_DEFAULT:
                holder.tvName.setText("");
                holder.itemView.setVisibility(View.INVISIBLE);
                break;
            case LoadMoreScrollListener.State.STATE_LOADING:
                holder.tvName.setText("正在加载...");
                break;
            case LoadMoreScrollListener.State.STATE_FAILED:
                holder.tvName.setText("加载失败,再试一次!");
                break;
            case LoadMoreScrollListener.State.STATE_SUCCESS:
                holder.tvName.setText("加载成功");
                break;
            case LoadMoreScrollListener.State.STATE_NO_MORE:
                holder.tvName.setText("没有更多了~");
                break;
            default:
                holder.itemView.setVisibility(View.INVISIBLE);
                holder.tvName.setText("");
                break;
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mScrollListener.setOnLoadMoreListener(listener);
    }

    public int getLoadMoreState() {
        return this.mState;
    }

    public void setLoadMoreState(int state) {
        onStateChanged(state);
    }

    /**
     * 加载更多的状态回调
     */
    @Override
    public void onStateChanged(int state) {
        this.mState = state;
        mScrollListener.setState(state);
        notifyDataSetChanged();
    }

    public static class LoadMoreFooterViewHolder extends BaseViewHolder {
        public TextView tvName;

        public LoadMoreFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}

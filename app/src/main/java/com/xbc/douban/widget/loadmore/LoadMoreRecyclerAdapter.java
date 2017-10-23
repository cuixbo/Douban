package com.xbc.douban.widget.loadmore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbc.douban.R;

/**
 * Created by xiaobocui on 2017/7/25.
 */

public class LoadMoreRecyclerAdapter<T extends BaseRecyclerViewHolder> extends BaseRecyclerAdapter implements LoadMoreStateChangedListener {
    protected static final int TYPE_NORMAL = 0;
    protected static final int TYPE_FOOTER = 10;
    protected static final int TYPE_EMPTY = 20;
    protected int mState = 0;
    protected LoadMoreScrollListener mScrollListener = new LoadMoreScrollListener(this);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    public final BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View mFooterView = LayoutInflater.from(mContext).inflate(R.layout.item_movie_footer, parent, false);
            return new LoadMoreViewHolder(mFooterView);
        } else if (viewType == TYPE_NORMAL) {
            return onCreateViewHolderNormal(parent);
        }else if (viewType == TYPE_EMPTY) {
            View mFooterView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty, parent, false);
            return new EmptyViewHolder(mFooterView);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (holder instanceof LoadMoreRecyclerAdapter.LoadMoreViewHolder) {
                bindLoadMoreFooterView(((LoadMoreViewHolder) holder), position);
            }
            return;
        } else if (getItemViewType(position) == TYPE_NORMAL) {
            onBindViewHolderNormal((T) holder, position);
        }else if (getItemViewType(position) == TYPE_EMPTY) {
            bindEmptyView((EmptyViewHolder) holder, position);
        }
    }

    @Override
    public final int getItemCount() {
        return getItemCountNormal() + 1;
    }

    public T onCreateViewHolderNormal(ViewGroup parent) {
        return null;
    }

    public void onBindViewHolderNormal(T holder, int position) {

    }

    public int getItemCountNormal() {
        return 0;
    }


    @Override
    public final int getItemViewType(int position) {
        if (getItemCountNormal()==0) {
            return TYPE_EMPTY;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
        //return super.getItemViewType(position);
    }

    private void bindLoadMoreFooterView(LoadMoreViewHolder holder, int position) {

        holder.tvName.setOnClickListener(null);
        switch (mState) {
            case LoadMoreScrollListener.State.STATE_DEFAULT:
                holder.tvName.setText("加载更多");
                holder.itemView.setVisibility(View.INVISIBLE);
                break;
            case LoadMoreScrollListener.State.STATE_LOADING:
                holder.itemView.setVisibility(View.VISIBLE);
                holder.tvName.setText("正在加载...");
                break;
            case LoadMoreScrollListener.State.STATE_FAILED:
                holder.itemView.setVisibility(View.VISIBLE);
                holder.tvName.setText("加载失败,再试一次");
                holder.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mScrollListener.loadMore();
                    }
                });
                break;
            case LoadMoreScrollListener.State.STATE_SUCCESS:
                holder.tvName.setText("加载更多");
                break;
            case LoadMoreScrollListener.State.STATE_NO_MORE:
                holder.tvName.setText("没有更多了~");
                break;
            case LoadMoreScrollListener.State.VISIBLE:
                holder.itemView.setVisibility(View.VISIBLE);
                break;
            default:
                holder.itemView.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void bindEmptyView(EmptyViewHolder holder, int position) {
       // holder.tvEmpty.setOnClickListener(null);
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
     * 加载更多Listener的状态回调
     */
    @Override
    public void onStateChanged(int state) {
        this.mState = state;
        mScrollListener.setState(state);
        notifyDataSetChanged();
    }

    public static class LoadMoreViewHolder extends BaseRecyclerViewHolder {
        public TextView tvName;

        public LoadMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class EmptyViewHolder extends BaseRecyclerViewHolder {
        public TextView tvEmpty;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvEmpty = (TextView) itemView.findViewById(R.id.tv_empty);
        }
    }
}

package com.xbc.douban.widget.loadmore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbc.douban.R;

public class BaseRecyclerAdapter<T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected static final int TYPE_EMPTY = 20;
    protected static final int TYPE_NORMAL = 0;

    protected Context mContext;
    protected RecyclerViewHelper.OnRecycleViewItemClickListener mItemClickListener;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY) {
            View mFooterView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, parent, false);
            return new EmptyViewHolder(mFooterView);
        } else if (viewType == TYPE_NORMAL) {
            return onCreateViewHolderNormal(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_EMPTY) {
            bindEmptyView((EmptyViewHolder) holder);
        } else if (getItemViewType(position) == TYPE_NORMAL) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, position);
                    }
                }
            });
            onBindViewHolderNormal((T) holder, position);
        }
    }

    public T onCreateViewHolderNormal(ViewGroup parent) {
        return null;
    }

    public void onBindViewHolderNormal(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (getItemCountNormal() == 0) {
            return 1;
        } else {
            return getItemCountNormal();
        }
    }

    public int getItemCountNormal() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCountNormal() == 0) {
            return TYPE_EMPTY;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void bindEmptyView(EmptyViewHolder holder) {

    }

    public void setOnItemClickListener(RecyclerViewHelper.OnRecycleViewItemClickListener listener) {
        mItemClickListener = listener;
    }

    public static class EmptyViewHolder extends BaseRecyclerViewHolder {
        public TextView tvEmpty;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvEmpty = (TextView) itemView.findViewById(R.id.tv_empty);
        }
    }
}

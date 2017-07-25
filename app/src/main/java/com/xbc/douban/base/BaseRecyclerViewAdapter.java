package com.xbc.douban.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaobocui on 2017/7/19.
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected static final int TYPE_NORMAL = 1;
    protected static final int TYPE_HEADER = 2;
    protected static final int TYPE_FOOTER = 3;
    protected static final int TYPE_AD = 4;
    protected Context mContext;
    protected RecyclerViewHelper.OnRecycleViewItemClickListener mItemClickListener;
    protected boolean hasHeader, hasFooter;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int extraCount = (hasHeader ? 1 : 0) + (hasFooter ? 1 : 0);
        return extraCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHeader && position == 0) {
            return TYPE_HEADER;
        }
        if (hasFooter && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void setOnItemClickListener(RecyclerViewHelper.OnRecycleViewItemClickListener listener) {
        mItemClickListener = listener;
    }
}

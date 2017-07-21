package com.xbc.douban.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaobocui on 2017/7/19.
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected RecyclerViewHelper.OnRecycleViewItemClickListener mItemClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
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
        return 0;
    }

    public void setOnItemClickListener(RecyclerViewHelper.OnRecycleViewItemClickListener listener) {
        mItemClickListener = listener;
    }
}

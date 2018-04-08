package com.xbc.douban.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    public View itemView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public BaseRecyclerViewHolder(Context context, ViewGroup parent, int layoutId) {
        this(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T get(int id) {
        return (T) itemView.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        return (T) view.findViewById(id);
    }

    public View getContainer() {
        return itemView;
    }

    public Context getContext() {
        if (null != itemView) {
            return itemView.getContext();
        }
        return null;
    }
}

package com.xbc.douban.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaobocui on 2017/7/19.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
    }
}

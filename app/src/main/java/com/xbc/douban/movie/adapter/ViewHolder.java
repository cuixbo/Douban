package com.xbc.douban.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xbc.douban.R;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public ImageView ivImage;
    public TextView tvName;
    public RatingBar rbStars;
    public TextView tvDirector;
    public TextView tvCast;
    public TextView tvCollect;

    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        this.rbStars = (RatingBar) itemView.findViewById(R.id.rb_stars);
        this.tvDirector = (TextView) itemView.findViewById(R.id.tv_director);
        this.tvCast = (TextView) itemView.findViewById(R.id.tv_cast);
        this.tvCollect = (TextView) itemView.findViewById(R.id.tv_collect);
    }
}

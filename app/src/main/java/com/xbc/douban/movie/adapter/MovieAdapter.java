package com.xbc.douban.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.api.GlideApp;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    private List<SubjectsBean> mData;

    public MovieAdapter(List<SubjectsBean> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(View.inflate(mContext, R.layout.item_hot_movie, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubjectsBean itemBean = mData.get(position);
        GlideApp.with(holder.itemView)
                .load(itemBean.images.large)
                .into(holder.ivImage);
        holder.tvName.setText(itemBean.title);
        holder.rbStars.setRating((float)(itemBean.rating.average/itemBean.rating.max)*5);
        holder.tvCast.setText(itemBean.casts.get(0).name);
        holder.tvDirector.setText(itemBean.directors.get(0).name);
        holder.tvCollect.setText(itemBean.collect_count + "人看过");
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }
}

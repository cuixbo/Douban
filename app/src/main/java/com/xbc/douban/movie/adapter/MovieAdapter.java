package com.xbc.douban.movie.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.api.GlideApp;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class MovieAdapter extends BaseRecyclerViewAdapter {
    private List<SubjectsBean> mData;

    public MovieAdapter(List<SubjectsBean> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);//需要调用super,在supper中对context初始化
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_movie, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//需要调用super,在supper中对click listener初始化
        SubjectsBean itemBean = mData.get(position);
        GlideApp.with(holder.itemView)
                .load(itemBean.images.large)
                .into(holder.ivImage);
        holder.tvName.setText(itemBean.title);
        holder.rbStars.setRating((float) (itemBean.rating.average / itemBean.rating.max) * 5);
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

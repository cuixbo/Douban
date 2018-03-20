package com.xbc.douban.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xbc.douban.R;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.widget.loadmore.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<SubjectsBean> mData;

    public CommentsAdapter() {
        mData = new ArrayList<SubjectsBean>();
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
        mData.add(new SubjectsBean());
    }

    public List<SubjectsBean> getData() {
        return mData;
    }

    public void setData(@NonNull List<SubjectsBean> mData) {
        this.mData = mData;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mFooterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_comments, parent, false);
        return new MovieViewHolder(mFooterView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder holder1 = ((MovieViewHolder) holder);
            holder1.ivImage.setImageResource(R.drawable.ic_hot_normal);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MovieViewHolder extends BaseRecyclerViewHolder {
        public ImageView ivImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }


}

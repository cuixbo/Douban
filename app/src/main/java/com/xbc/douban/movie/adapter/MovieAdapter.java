package com.xbc.douban.movie.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xbc.douban.R;
import com.xbc.douban.api.GlideApp;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.widget.loadmore.BaseRecyclerViewHolder;
import com.xbc.douban.widget.loadmore.LoadMoreRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends LoadMoreRecyclerAdapter<MovieAdapter.MovieViewHolder> {

    private List<SubjectsBean> mData;

    public MovieAdapter() {
        mData = new ArrayList<SubjectsBean>();
    }

    public List<SubjectsBean> getData() {
        return mData;
    }

    public void setData(@NonNull List<SubjectsBean> mData) {
        this.mData = mData;
    }

    @Override
    public MovieViewHolder onCreateViewHolderNormal(ViewGroup parent) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_hot_movie, parent, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolderNormal(MovieViewHolder holder, int position) {
        // super.onBindViewHolderNormal(holder, position);
        bindMovieView(holder, position);
    }

    @Override
    public int getItemCountNormal() {
        return mData.size();
    }


    private void bindMovieHeaderView(MovieHeaderViewHolder holder, int position) {
        holder.tvName.setText("what?this is header");
    }

    private void bindMovieAdvertiseView(MovieAdvertiseViewHolder holder, int position) {
        holder.tvName.setText("what?this is advertise");
    }

    private void bindMovieView(MovieViewHolder holder, int position) {
        SubjectsBean itemBean = mData.get(position);
        StringBuffer director = new StringBuffer();
        if (itemBean.directors != null && itemBean.directors.size() > 0) {
            for (int i = 0; i < itemBean.directors.size(); i++) {
                director.append(itemBean.directors.get(i).name);
                if (i != itemBean.directors.size() - 1) {
                    director.append(",");
                }
            }
        }
        StringBuffer cast = new StringBuffer();
        if (itemBean.casts != null && itemBean.casts.size() > 0) {
            for (int i = 0; i < itemBean.casts.size(); i++) {
                cast.append(itemBean.casts.get(i).name);
                if (i == 2) {
                    break;
                }
                if (i != itemBean.casts.size() - 1) {
                    cast.append("/");
                }
            }
        }
        if (itemBean.images!=null) {
            GlideApp.with(holder.itemView)
                    .load(itemBean.images.large)
                    .into(holder.ivImage);
        }
        holder.tvName.setText(itemBean.title);
        if (itemBean.rating!=null) {
            holder.rbStars.setRating((float) (itemBean.rating.average / itemBean.rating.max) * 5);
        }
        holder.tvDirector.setText("导演:" + director);
        holder.tvCast.setText("演员:" + cast);
        holder.tvCollect.setText(itemBean.collect_count + "人看过");
    }


    public static class MovieHeaderViewHolder extends BaseRecyclerViewHolder {
        public TextView tvName;

        public MovieHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class MovieAdvertiseViewHolder extends BaseRecyclerViewHolder {
        public TextView tvName;
        public ImageView ivImage;

        public MovieAdvertiseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class MovieViewHolder extends BaseRecyclerViewHolder {
        public ImageView ivImage;
        public TextView tvName;
        public RatingBar rbStars;
        public TextView tvDirector;
        public TextView tvCast;
        public TextView tvCollect;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.rbStars = (RatingBar) itemView.findViewById(R.id.rb_stars);
            this.tvDirector = (TextView) itemView.findViewById(R.id.tv_director);
            this.tvCast = (TextView) itemView.findViewById(R.id.tv_cast);
            this.tvCollect = (TextView) itemView.findViewById(R.id.tv_collect);
        }
    }


}

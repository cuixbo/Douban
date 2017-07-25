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
import com.xbc.douban.base.BaseLoadMoreRecyclerViewAdapter;
import com.xbc.douban.base.BaseViewHolder;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class MovieAdapter extends BaseLoadMoreRecyclerViewAdapter {

    private List<SubjectsBean> mData;

    public MovieAdapter() {
        mData = new ArrayList<SubjectsBean>();
        hasFooter = true;
    }

    public List<SubjectsBean> getData() {
        return mData;
    }

    public void setData(@NonNull List<SubjectsBean> mData) {
        this.mData = mData;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new MovieHeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_header, parent, false));
        } else if (viewType == TYPE_AD) {
            return new MovieAdvertiseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_advertise, parent, false));
        }
        if (viewType == TYPE_NORMAL) {
            return new MovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_movie, parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof MovieHeaderViewHolder) {
                bindMovieHeaderView(((MovieHeaderViewHolder) holder), position);
            }
            return;
        }
        if (getItemViewType(position) == TYPE_AD) {
            if (holder instanceof MovieAdvertiseViewHolder) {
                bindMovieAdvertiseView(((MovieAdvertiseViewHolder) holder), position);
            }
            return;
        }
        if (getItemViewType(position) == TYPE_NORMAL) {
            super.onBindViewHolder(holder, position);//需要调用super,在supper中对click listener初始化
            if (holder instanceof MovieViewHolder) {
                bindMovieView(((MovieViewHolder) holder), position);
            }
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int superViewType = super.getItemViewType(position);
        if (superViewType != 0) {
            return superViewType;
        }
        if (mData.size() > 0) {
            int dataIndex = position - (hasHeader ? 1 : 0);
            if ("悟空传".equals(mData.get(dataIndex).title)) {
                return TYPE_AD;
            } else {
                return TYPE_NORMAL;
            }
        }
        return super.getItemViewType(position);
    }

    private void bindMovieHeaderView(MovieHeaderViewHolder holder, int position) {
        holder.tvName.setText("what?this is header");
    }

    private void bindMovieAdvertiseView(MovieAdvertiseViewHolder holder, int position) {
        holder.tvName.setText("what?this is advertise");
    }

    private void bindMovieView(MovieViewHolder holder, int position) {
        SubjectsBean itemBean = mData.get(position - (hasHeader ? 1 : 0));
        GlideApp.with(holder.itemView)
                .load(itemBean.images.large)
                .into(holder.ivImage);
        holder.tvName.setText(itemBean.title);
        holder.rbStars.setRating((float) (itemBean.rating.average / itemBean.rating.max) * 5);
        holder.tvCast.setText(itemBean.casts.get(0).name);
        holder.tvDirector.setText(itemBean.directors.get(0).name);
        holder.tvCollect.setText(itemBean.collect_count + "人看过");
    }


    public static class MovieHeaderViewHolder extends BaseViewHolder {
        public TextView tvName;

        public MovieHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class MovieAdvertiseViewHolder extends BaseViewHolder {
        public TextView tvName;
        public ImageView ivImage;

        public MovieAdvertiseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class MovieViewHolder extends BaseViewHolder {
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

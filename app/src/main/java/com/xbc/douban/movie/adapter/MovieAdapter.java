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
import com.xbc.douban.base.BaseRecyclerViewAdapter;
import com.xbc.douban.base.BaseViewHolder;
import com.xbc.douban.movie.model.SubjectsBean;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class MovieAdapter extends BaseRecyclerViewAdapter {
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_AD = 3;
    private List<SubjectsBean> mData;

    public MovieAdapter(@NonNull List<SubjectsBean> mData) {
        this.mData = mData;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);//需要调用super,在supper中对context初始化
        if (viewType == TYPE_HEADER) {
            return new MovieHeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_header, parent, false));
        } else if (viewType == TYPE_FOOTER) {
            return new MovieFooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_footer, parent, false));
        } else if (viewType == TYPE_AD) {
            return new MovieAdvertiseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_advertise, parent, false));
        }
        return new MovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof MovieHeaderViewHolder) {
                bindMovieHeaderView(((MovieHeaderViewHolder) holder), position);
            }
            return;
        }
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (holder instanceof MovieFooterViewHolder) {
                bindMovieFooterView(((MovieFooterViewHolder) holder), position);
            }
            return;
        }
        if (getItemViewType(position) == TYPE_AD) {
            if (holder instanceof MovieAdvertiseViewHolder) {
                bindMovieAdvertiseView(((MovieAdvertiseViewHolder) holder), position);
            }
            return;
        }
        super.onBindViewHolder(holder, position);//需要调用super,在supper中对click listener初始化
        if (holder instanceof MovieViewHolder) {
            bindMovieView(((MovieViewHolder) holder), position);
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0 + 2;
        }
        return mData.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        if ("悟空传".equals(mData.get(position - 1).title)) {
            return TYPE_AD;
        }
        return super.getItemViewType(position);
    }

    private void bindMovieHeaderView(MovieHeaderViewHolder holder, int position) {
        holder.tvName.setText("what?this is header");
    }

    private void bindMovieFooterView(MovieFooterViewHolder holder, int position) {
        holder.tvName.setText("what?this is footer");
    }

    private void bindMovieAdvertiseView(MovieAdvertiseViewHolder holder, int position) {
        holder.tvName.setText("what?this is advertise");
    }

    private void bindMovieView(MovieViewHolder holder, int position) {
        SubjectsBean itemBean = mData.get(position - 1);
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

    public static class MovieFooterViewHolder extends BaseViewHolder {
        public TextView tvName;

        public MovieFooterViewHolder(@NonNull View itemView) {
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

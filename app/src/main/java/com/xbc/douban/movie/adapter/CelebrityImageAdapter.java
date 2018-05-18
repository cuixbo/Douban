package com.xbc.douban.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbc.douban.R;
import com.xbc.douban.api.GlideApp;
import com.xbc.douban.movie.model.SubjectsBean.CastsBean;
import com.xbc.douban.widget.loadmore.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 名人图片适配器
 */
public class CelebrityImageAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<CastsBean> mData;

    public CelebrityImageAdapter() {
        mData = new ArrayList<>();
    }

    public List<CastsBean> getData() {
        return mData;
    }

    public void setData(@NonNull List<CastsBean> mData) {
        this.mData = mData;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_celebrity, parent, false);
        return new CelebrityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (holder instanceof CelebrityViewHolder) {
            CelebrityViewHolder celebrityHolder = ((CelebrityViewHolder) holder);
            celebrityHolder.ivImage.setImageResource(R.drawable.movie_default_large);
            CastsBean castsBean = mData.get(position);
            if (castsBean.avatars != null) {
                GlideApp.with(holder.itemView)
                        .load(castsBean.avatars.medium)
                        .into(celebrityHolder.ivImage);
                Log.e("xbc", castsBean.avatars.medium);
            }
            celebrityHolder.tvName.setText(castsBean.name);
            celebrityHolder.tvPosition.setText(castsBean.type);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CelebrityViewHolder extends BaseRecyclerViewHolder {
        public ImageView ivImage;
        public TextView tvName, tvPosition;

        public CelebrityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
        }
    }


}

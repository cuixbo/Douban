package com.xbc.douban.movie.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xbc.douban.R;
import com.xbc.douban.api.GlideApp;
import com.xbc.douban.base.BaseActivity;
import com.xbc.douban.movie.adapter.CelebrityImageAdapter;
import com.xbc.douban.movie.contract.MovieDetailContract;
import com.xbc.douban.movie.model.SubjectsBean;
import com.xbc.douban.movie.presenter.MovieDetailPresenter;
import com.xbc.douban.widget.IconView;

import java.util.ArrayList;
import java.util.List;


public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {
    private MovieDetailContract.Presenter mPresenter;

    private RecyclerView mCelebrityRecyclerView;
    private CelebrityImageAdapter mCelebrityAdapter;
    private IconView mBtnLeft;
    private TextView mTvTitle;
    private IconView mBtnRight;
    private RelativeLayout mLlTitle;
    private ImageView mIvPoster;
    private FrameLayout mFlMoviePoster;
    private TextView mTvName;
    private TextView mTvType;
    private TextView mTvOriginalName;
    private TextView mTvDate;
    private TextView mTvDuration;
    private TextView mTvScore;
    private RatingBar mRbStars;
    private TextView mTvScoredCount;
    private TextView mTvIntro;

    private SubjectsBean mMovieSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MovieDetailPresenter(this);
        setContentView(R.layout.activity_movie_detail);
        initView();
        initListener();
        mPresenter.start();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        mBtnLeft = (IconView) findViewById(R.id.btn_left);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnRight = (IconView) findViewById(R.id.btn_right);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mIvPoster = (ImageView) findViewById(R.id.iv_poster);
        mFlMoviePoster = (FrameLayout) findViewById(R.id.fl_movie_poster);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mTvOriginalName = (TextView) findViewById(R.id.tv_original_name);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvDuration = (TextView) findViewById(R.id.tv_duration);
        mTvScore = (TextView) findViewById(R.id.tv_score);
        mRbStars = (RatingBar) findViewById(R.id.rb_stars);
        mTvScoredCount = (TextView) findViewById(R.id.tv_scored_count);
        mTvIntro = (TextView) findViewById(R.id.tv_intro);
        mCelebrityRecyclerView = (RecyclerView) findViewById(R.id.celebrity_recycler_view);

        mCelebrityRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        mCelebrityAdapter = new CelebrityImageAdapter();

        mCelebrityRecyclerView.setAdapter(mCelebrityAdapter);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public String getMovieId() {
        return getIntent().getStringExtra(Intent.EXTRA_UID);
    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void notifyDataSetChanged(List<SubjectsBean> subjects, boolean append) {

    }

    @Override
    public void setRefresh(boolean refresh) {

    }

    @Override
    public void updateMovieSubject(SubjectsBean subjectsBean) {
        mTvTitle.setText(subjectsBean.title);
        GlideApp.with(this)
                .load(subjectsBean.images.large)
                .into(mIvPoster);
        //基本信息
        mTvName.setText(subjectsBean.title);
        mTvType.setText(TextUtils.join("/", subjectsBean.genres));
        mTvOriginalName.setText("原名:" + subjectsBean.original_title);
        mTvDate.setText("首映时间:" + subjectsBean.year);
        mTvDuration.setText("");

        mTvScore.setText(String.valueOf(subjectsBean.rating.average));
        mRbStars.setRating((float) (subjectsBean.rating.average / subjectsBean.rating.max) * 5);
        mTvScoredCount.setText(subjectsBean.ratings_count + "人");

        mTvIntro.setText(subjectsBean.summary);

        //影人
        List<SubjectsBean.CastsBean> celebrities = new ArrayList<>();
        if (subjectsBean.directors != null && !subjectsBean.directors.isEmpty()) {
            subjectsBean.directors.get(0).type = "导演";
            celebrities.add(subjectsBean.directors.get(0));
        }
        if (subjectsBean.casts != null && !subjectsBean.casts.isEmpty()) {
            for (SubjectsBean.CastsBean castsBean : subjectsBean.casts) {
                castsBean.type = "演员";
            }
            celebrities.addAll(subjectsBean.casts);
        }
        if (!celebrities.isEmpty()) {
            mCelebrityAdapter.setData(celebrities);
            mCelebrityAdapter.notifyDataSetChanged();
        }
    }

}

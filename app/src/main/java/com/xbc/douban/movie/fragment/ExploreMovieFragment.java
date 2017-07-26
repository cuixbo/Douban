package com.xbc.douban.movie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class ExploreMovieFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments=new ArrayList<Fragment>();
    private String[] mTitles=new String[]{"电视","电影"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initView() {
        mTabLayout= (TabLayout) getView().findViewById(R.id.tab_layout);
        mViewPager= (ViewPager) getView().findViewById(R.id.view_pager);

        mFragments.add(new MineFragment());
        mFragments.add(new MineFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        //去除Tab默认的Ripple效果
        if (mTabLayout.getChildCount()>0) {
            ViewGroup tabStrip=  ((ViewGroup) mTabLayout.getChildAt(0));
            if (tabStrip!=null&&tabStrip.getChildCount()>0) {
                for(int i=0;i<tabStrip.getChildCount();i++){
                    if (tabStrip.getChildAt(i)!=null) {
                        tabStrip.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        }

    }

    @Override
    public void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}

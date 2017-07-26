package com.xbc.douban.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbc.douban.R;
import com.xbc.douban.base.BaseFragment;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class MineFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}

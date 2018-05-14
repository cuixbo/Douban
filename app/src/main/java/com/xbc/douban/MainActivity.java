package com.xbc.douban;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.xbc.douban.base.BaseActivity;
import com.xbc.douban.movie.fragment.HotMovieFragment;


public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl_fragment_container, new HotMovieFragment()).commitAllowingStateLoss();
    }

}

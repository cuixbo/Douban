package com.xbc.douban;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xbc.douban.base.BaseActivity;
import com.xbc.douban.movie.fragment.ExploreMovieFragment;
import com.xbc.douban.movie.fragment.HotMovieFragment;
import com.xbc.douban.movie.fragment.MineFragment;


public class MainActivity extends BaseActivity {

    private BottomNavigationBar bottomNavigationBar;
    private FragmentManager fragmentManager;
    private final static String TAG_HOT = "热映";
    private final static String TAG_EXPLORER = "找片";
    private final static String TAG_MINE = "我的";
    private final static String[] TAGS = new String[]{"热映", "找片", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        bottomNavigationBar.selectTab(0);
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_hot_normal, "热映"))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_normal, "找片"))
                .addItem(new BottomNavigationItem(R.drawable.ic_mine_normal, "我的"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();
    }

    private void initListener() {
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //隐藏与显示fragment
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentByTag(TAGS[position]);
                if (fragment == null) {
                    switch (position) {
                        case 0:
                            fragment = new HotMovieFragment();
                            break;
                        case 1:
                            fragment = new ExploreMovieFragment();
                            break;
                        case 2:
                            fragment = new MineFragment();
                            break;
                    }
                    if (fragment != null) {
                        transaction.add(R.id.fl_fragment_container, fragment, TAGS[position]);
                    }
                } else {
                    transaction.show(fragment);
                }
                transaction.commitAllowingStateLoss();

                //切换事件
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentByTag(TAGS[position]);
                if (fragment != null) {
                    transaction.hide(fragment);
                    transaction.commitAllowingStateLoss();
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}

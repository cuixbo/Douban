package com.xbc.douban.movie.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xbc.douban.MainActivity;
import com.xbc.douban.R;
import com.xbc.douban.base.BaseActivity;


public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initListener();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                }
            }
        }, 2000);
    }

    @Override
    protected void initListener() {

    }
}

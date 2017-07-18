package com.xbc.douban.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log();
    }

    @Override
    protected void onStart() {
        super.onStart();
        log();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log();
    }

    @Override
    protected void onPause() {
        super.onPause();
        log();
    }

    @Override
    protected void onStop() {
        super.onStop();
        log();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        log();
    }

    public void log(String log) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName() + ":";
        }
        Log.e("xbc", prefix + log);
    }

    public void log() {
        if (System.currentTimeMillis()!=0) {
            return;
        }
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName();
        }
        Log.e("xbc", prefix);
    }
}

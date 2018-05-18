package com.xbc.douban.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log("Context");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        log("Activity");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log();
        //fragment恢复后,恢复到原来的show,hide状态
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        log();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log();
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onStart() {
        super.onStart();
        log();
    }

    @Override
    public void onResume() {
        super.onResume();
        log();
    }

    @Override
    public void onPause() {
        super.onPause();
        log();
    }

    @Override
    public void onStop() {
        super.onStop();
        log();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log(this.hashCode() + "");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        log();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log();
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        log();
    }

    public void log(String log) {
        if (System.currentTimeMillis() != 0) {
            // return;
        }
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName() + ":";
        }
        Log.e("xbc", prefix + log);
    }

    public void log() {
        if (System.currentTimeMillis() != 0) {
            return;
        }
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName();
        }
        Log.e("xbc", prefix);
    }

    protected abstract void initIntent();

    protected abstract void initView();

    protected abstract void initListener();

}

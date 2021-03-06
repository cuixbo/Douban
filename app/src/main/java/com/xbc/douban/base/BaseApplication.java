package com.xbc.douban.base;

import android.app.Application;

/**
 * Created by xiaobocui on 2017/7/13.
 */

public class BaseApplication extends Application {
    protected static Application APPLICATION;

    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION=this;
    }

    public static Application getApplication(){
        return APPLICATION;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

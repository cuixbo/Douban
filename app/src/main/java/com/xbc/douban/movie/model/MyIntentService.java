package com.xbc.douban.movie.model;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.xbc.douban.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.log("onHandleIntent:"+this.hashCode());
    }
}
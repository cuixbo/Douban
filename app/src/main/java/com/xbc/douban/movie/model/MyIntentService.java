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
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(1000);
                Log.log(this.hashCode()+":"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.xbc.douban.api;


import io.reactivex.Observer;
import retrofit2.Callback;

/**
 * Created by xiaobocui on 2017/10/24.
 */

public abstract class ProxyCallback<T> implements Callback<T>, Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onFailed(int code, String msg);

    public abstract boolean onError(Throwable t, String msg);

    public abstract void onCompleted();

}

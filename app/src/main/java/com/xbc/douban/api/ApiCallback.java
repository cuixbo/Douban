package com.xbc.douban.api;


import android.util.Log;

import com.xbc.douban.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaobocui on 2017/10/24.
 */

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
        onComplete();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ApiException e = ExceptionEngine.handleException(t);
        if (e.getCause() instanceof ServerException) {
            onFailed(e.code,e.message);
        } else {
            boolean handleError = onError(t);
            if (handleError) {
                ApiException ex = ExceptionEngine.handleException(t);
                Log.e("onError:", e.message);
                ToastUtil.show(e.message);
            }
        }
        onComplete();
    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(int code,String msg);

    public boolean onError(Throwable t) {
        Log.e("xbc", "abs.onError");
        return false;
    }

    public abstract void onComplete();

}

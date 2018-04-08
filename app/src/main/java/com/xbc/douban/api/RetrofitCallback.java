package com.xbc.douban.api;


import com.xbc.douban.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit回调,现在可以使用ApiCallback
 */
@Deprecated
public abstract class RetrofitCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
        onComplete();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ApiException e = ExceptionEngine.handleException(t);
        if (e.getCause() instanceof ServerException) {
            onFailed(e.code, e.message);
        } else {
            boolean handleError = onError(t);
            if (handleError) {
                ToastUtil.show(e.message);
            }
        }
        onComplete();
    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(int code, String msg);

    public boolean onError(Throwable t) {
        return false;
    }

    public abstract void onComplete();

}

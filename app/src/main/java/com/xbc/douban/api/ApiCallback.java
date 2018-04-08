package com.xbc.douban.api;


import com.xbc.douban.util.ToastUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 实现了Retrofit和RxJava的回调方式
 */
public abstract class ApiCallback<T> extends ProxyCallback<T> {

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
        onCompleted();
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        ApiException e = ExceptionEngine.handleException(t);
        if (e.getCause() instanceof ServerException) {
            onFailed(e.code, e.message);
        } else {
            boolean handleError = onError(t, t.getLocalizedMessage());
            if (handleError) {
                ToastUtil.show(e.message);
            }
        }
        onCompleted();
    }


    @Override
    public final void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public final void onNext(@NonNull T t) {
        onSuccess(t);
        onCompleted();
    }

    @Override
    public final void onError(@NonNull Throwable t) {
        ApiException e = ExceptionEngine.handleException(t);
        if (e.getCause() instanceof ServerException) {
            onFailed(e.code, e.message);
        } else {
            boolean handleError = onError(t, t.getLocalizedMessage());
            if (handleError) {
                ToastUtil.show(e.message);
            }
        }
        onCompleted();
    }

    @Override
    public final void onComplete() {

    }

    /**
     * @param t
     * @param msg
     * @return true:显示系统错误提示.  false:不显示错误提示,交由业务自己处理
     */
    public boolean onError(Throwable t, String msg) {
        return false;
    }

}

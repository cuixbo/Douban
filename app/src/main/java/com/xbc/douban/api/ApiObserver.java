package com.xbc.douban.api;

import com.xbc.douban.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * RxJava Observer回调,现在可以使用ApiCallback
 */
@Deprecated
public class ApiObserver<T> implements Observer<T> {

    public ApiCallback<T> callback;

    public ApiObserver(ApiCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        callback.onSuccess(t);
        callback.onComplete();
    }

    @Override
    public void onError(@NonNull Throwable t) {
        ApiException e = ExceptionEngine.handleException(t);
        if (e.getCause() instanceof ServerException) {
            callback.onFailed(e.code, e.message);
        } else {
            boolean handleError = callback.onError(t, t.getLocalizedMessage());
            if (handleError) {
                ToastUtil.show(e.message);
            }
        }
        callback.onComplete();
    }

    @Override
    public void onComplete() {

    }
}

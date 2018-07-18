package com.xzydonate.base.presenter.base;

import com.xzydonate.base.entity.BaseResp;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by dell on 2018/4/25.
 */

public class BaseObserver<T> implements Observer<BaseResp<T>> {

    public OnCallback<T> listener = null;

    public BaseObserver(OnCallback<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseResp<T> resp) {
        if (listener != null) {
            if (listener != null && resp.isError()) {
                listener.onCall(resp.getResults());
            } else {
                listener.onError("-101", "" + resp.getResults());
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (listener != null)
            listener.onError("-100", e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public interface OnCallback<T> {

        void onCall(T response);

        void onError(String errCode, String errMsg);
    }

}
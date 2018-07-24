package com.xzydonate.basesdk.presenter.base;


import com.xzydonate.basesdk.entity.WanResp;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by dell on 2018/4/25.
 */

public class WanObserver<T> implements Observer<WanResp<T>> {

    public OnCallback<T> listener = null;

    public WanObserver(OnCallback<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull WanResp<T> resp) {
        if (listener != null) {
            if (listener != null && resp.getErrorCode() == 0) {
                listener.onCall(resp.getData());
            } else {
                listener.onError(resp.getErrorCode() + "", "" + resp.getErrorMsg());
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
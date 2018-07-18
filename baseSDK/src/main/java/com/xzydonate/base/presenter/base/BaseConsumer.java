package com.xzydonate.base.presenter.base;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by dell on 2018/4/25.
 */

public class BaseConsumer<T> implements Consumer<T> {

    public OnStorageListener listener = null;

    public BaseConsumer(OnStorageListener listener){
        this.listener = listener;
    }

    @Override
    public void accept(@NonNull T t) {
        if (listener != null)
            listener.onStorage(t);
    }

    public interface OnStorageListener<T> {
        void onStorage(T data);
    }


}

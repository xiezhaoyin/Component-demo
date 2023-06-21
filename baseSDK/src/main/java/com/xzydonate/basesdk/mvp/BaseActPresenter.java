package com.xzydonate.basesdk.mvp;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018/4/24.
 */

public class BaseActPresenter {

    private RxAppCompatActivity activity;

    public BaseActPresenter(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public void destroyPresenter() {
        activity = null;
    }


    public <T> void setConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer) {
        if (activity == null) {
            return;
        }

        observable.subscribeOn(Schedulers.io()) //请求在工作线程
                .compose(activity.<T>bindToLifecycle())
                .observeOn(Schedulers.io())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        if (activity == null) {
            return;
        }

        observable.subscribeOn(Schedulers.io()) //请求在IO线程
                .compose(activity.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }


}

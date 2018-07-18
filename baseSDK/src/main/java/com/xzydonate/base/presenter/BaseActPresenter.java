package com.xzydonate.base.presenter;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

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

    public <T> void setDelayConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer, int timeout) {
        if (activity == null) {
            return;
        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread()) //请求在工作线程
                .compose(activity.<T>bindToLifecycle())
                .observeOn(Schedulers.io())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer) {
        if (activity == null) {
            return;
        }

        observable.subscribeOn(Schedulers.newThread()) //请求在工作线程
                .observeOn(Schedulers.io())
                .compose(activity.<T>bindToLifecycle())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setDelaySubscribe(Observable<T> observable, Observer<T> observer, int timeout) {
        if (activity == null) {
            return;
        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()) //请求在IO线程
                .compose(activity.<T>bindToLifecycle())
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

    public void createPresenter(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public void destroyPresenter() {

    }


}

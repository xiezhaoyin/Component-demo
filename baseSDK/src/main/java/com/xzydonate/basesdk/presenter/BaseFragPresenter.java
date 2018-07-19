package com.xzydonate.basesdk.presenter;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018/4/24.
 */

public class BaseFragPresenter {

    private RxFragment fragment;

    public <T> void setDelayConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer, int timeout) {
        if (fragment == null) {
            return;
        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread()) //请求在工作线程
                .compose(fragment.<T>bindToLifecycle())
                .observeOn(Schedulers.io())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer) {
        if (fragment == null) {
            return;
        }

        observable.subscribeOn(Schedulers.newThread()) //请求在工作线程
                .observeOn(Schedulers.io())
                .compose(fragment.<T>bindToLifecycle())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setDelaySubscribe(Observable<T> observable, Observer<T> observer, int timeout) {
        if (fragment == null) {
            return;
        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()) //请求在IO线程
                .compose(fragment.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        if (fragment == null) {
            return;
        }

        observable.subscribeOn(Schedulers.io()) //请求在IO线程
                .compose(fragment.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public void createPresenter(RxFragment fragment) {
        this.fragment = fragment;
    }

    public void destroyPresenter() {

    }


}

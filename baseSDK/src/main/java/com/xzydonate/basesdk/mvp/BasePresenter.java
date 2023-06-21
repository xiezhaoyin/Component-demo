package com.xzydonate.basesdk.mvp;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter{

    protected M mModel;
    protected V mView;

    public BasePresenter() {
        createPresenter();
    }

    public BasePresenter(V view) {

        if(view != null){
            mView = view;
        }
        createPresenter();
    }

    public BasePresenter(M model, V view){
          if(model != null){
              mModel = model;
          }
          if(view != null){
              mView = view;
          }
          createPresenter();
    }

    @Override
    public void createPresenter() {

    }

    @Override
    public void destroyPresenter() {
           if(mModel != null){
               mModel.destroyModel();
           }
    }

    public <T> void setDelayConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer, int timeout) {
//        if (activity == null) {
//            return;
//        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread()) //请求在工作线程
//                .compose(activity.<T>bindToLifecycle())
                .observeOn(Schedulers.io())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setConsumerSubscribe(Observable<T> observable, Consumer<T> consumer, Observer<T> observer) {
//        if (activity == null) {
//            return;
//        }

        observable.subscribeOn(Schedulers.newThread()) //请求在工作线程
                .observeOn(Schedulers.io())
//                .compose(activity.<T>bindToLifecycle())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setDelaySubscribe(Observable<T> observable, Observer<T> observer, int timeout) {
//        if (activity == null) {
//            return;
//        }

        observable.delay(timeout, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()) //请求在IO线程
//                .compose(activity.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    public <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
//        if (activity == null) {
//            return;
//        }

        observable.subscribeOn(Schedulers.io()) //请求在IO线程
//                .compose(activity.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}

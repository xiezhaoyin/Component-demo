package com.xzydonate.basesdk.activity;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.presenter.BaseFragPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;


/**
 * Created by dell on 2018/3/16.
 */

public abstract class BaseEventFragment<T extends BaseFragPresenter> extends RxFragment implements IAttachEvent, IBaseView, OnReceiveListener {

    protected String TAG = null;
    private int layoutResId = -1;
    private EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;

    @Inject
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TAG = getClass().getSimpleName();

        layoutResId = this.createView(savedInstanceState);
        if (layoutResId != -1) {
            View view = inflater.inflate(layoutResId, null);
            unbinder = ButterKnife.bind(this, view);
            isCreated = true;

            if (dispatch == null) {
                dispatch = attachEvent(new EventDispatch(), this);
            }

//            AndroidSupportInjection.inject(this);
            if(presenter !=null) {
                presenter.createPresenter(this);
            }
            this.initView(savedInstanceState);
            return view;
        } else {
            throw new NullPointerException("createView don't be null");
        }
    }

    @Override
    public EventDispatch attachEvent(EventDispatch dispatch, OnReceiveListener listener) {
        dispatch.register(listener);
        return dispatch;
    }

    @Override
    public void detachEvent() {
        if (dispatch != null) {
            dispatch.unregister();
            dispatch = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isCreated) {
                this.resumeView();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter !=null) {
            presenter.destroyPresenter();
        }
        this.destroyView();
        unbinder.unbind();
        detachEvent();
    }

    protected void gotoActivity(Class cl, @Nullable Object object) {
        gotoActivity(cl, object, true);
    }

    protected void gotoActivity(Class cl, @Nullable Object object, boolean isSticky) {
        Intent intent = new Intent(getActivity(), cl);
        if (object != null && dispatch != null) {
            dispatch.post(object, isSticky);
        }
        startActivity(intent);
    }

}

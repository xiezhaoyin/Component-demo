package com.xzydonate.basesdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xzydonate.basesdk.R;
import com.xzydonate.basesdk.presenter.BaseActPresenter;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Created by dell on 2018/4/24.
 */
public abstract class BaseEventActivity<T extends BaseActPresenter> extends RxAppCompatActivity implements IAttachEvent, IBaseView, OnReceiveListener {

    protected String TAG = null;
    private int layoutResId = -1;
    protected EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;

    @Inject
    T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        layoutResId = this.createView(savedInstanceState);
        if (layoutResId != -1) {
            setContentView(layoutResId);
            unbinder = ButterKnife.bind(this);
            isCreated = true;
        } else {
            throw new NullPointerException("createView don't be null");
        }

        if (dispatch == null) {
            dispatch = attachEvent(new EventDispatch(), this);
        }

//        DaggerBaseActivityComponent.create().inject(this);
        if (presenter != null) {
            presenter.createPresenter(this);
        }
        this.initView(savedInstanceState);
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
    protected void onResume() {
        super.onResume();
        if (isCreated) {
            this.resumeView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
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
        Intent intent = new Intent(this, cl);
        if (object != null && dispatch != null) {
            dispatch.post(object, isSticky);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.still);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.still, R.anim.slide_left_out);
    }

}

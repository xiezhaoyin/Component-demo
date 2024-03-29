package com.xzydonate.basesdk.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.xzydonate.basesdk.R;
import com.xzydonate.basesdk.mvp.BaseActPresenter;
import com.xzydonate.basesdk.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dell on 2018/4/24.
 */
public abstract class BaseActivity<P extends BaseActPresenter> extends RxAppCompatActivity implements IAttachEvent, ILifecycleView, OnReceiveListener {

    protected String TAG = null;
    private int layoutResId = 0;
    protected EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        layoutResId = this.createView();
        if (layoutResId != 0) {
            setContentView(layoutResId);
            unbinder = ButterKnife.bind(this);
            isCreated = true;
        } else {
            throw new NullPointerException("createView don't be null");
        }

        this.initView(savedInstanceState);

        if (dispatch == null) {
            dispatch = attachEvent(new EventDispatch(), this);
        }

    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorStatusBar), 0);
        StatusBarUtil.setLightMode(this);
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

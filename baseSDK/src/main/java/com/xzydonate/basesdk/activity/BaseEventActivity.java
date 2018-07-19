package com.xzydonate.basesdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.xzydonate.basesdk.R;


import butterknife.ButterKnife;

/**
 * Created by dell on 2018/4/24.
 */
public abstract class BaseEventActivity extends AutoLayoutActivity implements IAttachEvent, IBaseView, OnReceiveListener {

    protected String TAG = null;
    protected EventDispatch dispatch = null;
    private boolean isCreated = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        int layoutResId = this.createView(savedInstanceState);
        if (layoutResId != -1) {
            setContentView(layoutResId);
            ButterKnife.bind(this);
            isCreated = true;
        }

        if (dispatch == null) {
            dispatch = attachEvent(new EventDispatch(), this);
            dispatch.register(this);
        }

        this.initView();
    }

    @Override
    public EventDispatch attachEvent(EventDispatch dispatch, OnReceiveListener listener) {
        return dispatch;
    }

    @Override
    public void detachEvent() {
        dispatch = null;
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
        this.destroyView();
        if (dispatch != null) {
            dispatch.unregister();
            detachEvent();
        }
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

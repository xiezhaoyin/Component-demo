package com.xzydonate.basesdk.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by dell on 2018/3/16.
 */

public abstract class BaseFragment<T extends BaseFragPresenter> extends RxFragment implements IAttachEvent, ILifecycleView, OnReceiveListener {

    protected String TAG = null;
    private int layoutResId = 0;
    private EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;
    protected int type = -1;

    @Inject
    protected T presenter;

    // 接收参数
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("type", -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();

        layoutResId = this.createView();
        if (layoutResId != 0) {
            View view = inflater.inflate(layoutResId, null);
            unbinder = ButterKnife.bind(this, view);
            isCreated = true;

            this.initView(savedInstanceState);

            if (dispatch == null) {
                dispatch = attachEvent(new EventDispatch(), this);
            }

            return view;
        } else {
            throw new NullPointerException("createView don't be null");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        this.destroyView();
        unbinder.unbind();
        detachEvent();
        if (presenter != null) {
            presenter.destroyPresenter();
        }
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

    // 携带参数
    public BaseFragment newInstance(BaseFragment fragment, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    // 更新参数
    public void updateArguments(int type) {
        this.type = type;
        Bundle args = getArguments();
        if (args != null) {
            args.putInt("type", type);
        }
    }


}

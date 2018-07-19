package com.xzydonate.basesdk.activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventDispatch {

    private OnReceiveListener listener = null;

    public void register(OnReceiveListener listener) {
        this.listener = listener;
        EventBus.getDefault().register(this);//订阅
    }

    public void unregister() {
        EventBus.getDefault().unregister(this);//取消订阅
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceive(Object receive) {
        if (listener != null)
            listener.onReceive(false, receive.getClass().getSimpleName(), receive);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventReceive(Object receive) {
        if (listener != null)
            listener.onReceive(true, receive.getClass().getSimpleName(), receive);
    }

    protected void post(Object object) {
        post(object, true);
    }

    protected void post(Object object, boolean isSticky) {
        if (isSticky) {
            EventBus.getDefault().postSticky(object);
        } else {
            EventBus.getDefault().post(object);
        }
    }

}

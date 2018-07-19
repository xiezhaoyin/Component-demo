package com.xzydonate.basesdk.activity;

public interface IAttachEvent {

    EventDispatch attachEvent(EventDispatch dispatch,OnReceiveListener listener);

    void detachEvent();
}

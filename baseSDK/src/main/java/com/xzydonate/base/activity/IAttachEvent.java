package com.xzydonate.base.activity;

public interface IAttachEvent {

    EventDispatch attachEvent(EventDispatch dispatch,OnReceiveListener listener);

    void detachEvent();
}

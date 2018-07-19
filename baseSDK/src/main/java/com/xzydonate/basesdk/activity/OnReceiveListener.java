package com.xzydonate.basesdk.activity;

public interface OnReceiveListener {
    void onReceive(boolean isSticky, String eventType, Object event);
}

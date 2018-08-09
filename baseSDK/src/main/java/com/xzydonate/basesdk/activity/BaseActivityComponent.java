package com.xzydonate.basesdk.activity;

import dagger.Component;


@Component
public interface BaseActivityComponent {

    void inject(BaseEventActivity activity);
}

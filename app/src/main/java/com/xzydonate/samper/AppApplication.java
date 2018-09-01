package com.xzydonate.samper;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class AppApplication extends DaggerApp {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}

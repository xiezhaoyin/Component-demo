package com.xzydonate.basesdk.activity;

import android.os.Bundle;

/**
 * Created by dell on 2018/4/25.
 */

public interface ILifecycleView {

    int createView();

    void initView(Bundle savedInstanceState);

    void resumeView();

    void destroyView();

}

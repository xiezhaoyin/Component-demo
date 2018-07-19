package com.xzydonate.basesdk.adapter.recyclerAdapter.indicator;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by boby on 2017/1/22.
 */
@IntDef({LoadMoreType.APAY, LoadMoreType.BALL_BEAT, LoadMoreType.BALL_CLIP_ROTATE, LoadMoreType.BALL_SCALE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadMoreType {

    public static final int APAY = 0;

    public static final int BALL_BEAT = 1;

    public static final int BALL_CLIP_ROTATE = 2;

    public static final int BALL_SCALE = 3;

}

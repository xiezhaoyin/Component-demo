package com.xzydonate.news.newsInfo;

import android.os.Bundle;

import com.xzydonate.basesdk.activity.BaseEventActivity;
import com.xzydonate.news.R;


public class NewsInfoActivity extends BaseEventActivity {

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.activity_news_page_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {
        if (isSticky) {
            switch (eventTag) {
                case "NewsInfo":
                    break;
            }
        } else {

        }
    }
}

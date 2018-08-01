package com.xzydonate.news.newsInfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xzydonate.basesdk.activity.BaseEventActivity;
import com.xzydonate.news.NewsResp;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;

import butterknife.BindView;


public class NewsInfoActivity extends BaseEventActivity {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.webView)
    WebView mWebView;
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
                    NewsResp.NewsInfo newsInfo = (NewsResp.NewsInfo) event;
                    mToolbar.setTitle(newsInfo.title);
                    initWebView();
                    mWebView.loadUrl(newsInfo.link);
                    break;
            }

        } else {

        }
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}

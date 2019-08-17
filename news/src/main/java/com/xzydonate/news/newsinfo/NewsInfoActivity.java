package com.xzydonate.news.newsinfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xzydonate.basesdk.activity.BaseActivity;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;
import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.project.ProjectResp;

import butterknife.BindView;


public class NewsInfoActivity extends BaseActivity {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.webView)
    WebView mWebView;

    @Override
    public int createView() {
        return R.layout.activity_news_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(v -> finish());
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
                case "Project":
                    ProjectResp.Project project = (ProjectResp.Project) event;
                    mToolbar.setTitle(project.title);
                    initWebView();
                    mWebView.loadUrl(project.link);
                    break;
                case "Article":
                    ArticleResp.Article article = (ArticleResp.Article) event;
                    mToolbar.setTitle(article.title);
                    initWebView();
                    mWebView.loadUrl(article.link);
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

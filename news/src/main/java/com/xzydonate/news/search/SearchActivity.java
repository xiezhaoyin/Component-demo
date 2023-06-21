package com.xzydonate.news.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.xzydonate.basesdk.activity.BaseActivity;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;
import com.xzydonate.news.article.ArticleResp;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements ISearchView {

    @BindView(R2.id.iv_back)
    ImageButton ivBack;
    @BindView(R2.id.iv_search)
    TextView ivSearch;
    @BindView(R2.id.edt_search)
    EditText edtSearch;
    @BindView(R2.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R2.id.flowLayout_hotKey)
    TagFlowLayout flowLayoutHotKey;
    @BindView(R2.id.iv_clear_history)
    ImageButton ivClearHistory;
    @BindView(R2.id.flowLayout_history)
    TagFlowLayout flowLayoutHistory;
    @BindView(R2.id.search_scroll_view)
    NestedScrollView searchScrollView;
    @BindView(R2.id.search_coordinator_group)
    CoordinatorLayout searchCoordinatorGroup;

    private SearchPresenter presenter = null;

    @Override
    public int createView() {
        return R.layout.news_activity_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new SearchPresenter(this);
        presenter.queryHotKey();
    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {
        presenter.destroyPresenter();
    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @Override
    public void queryHotKeySuccess(List<HotKeyResp> data) {

        flowLayoutHotKey.setAdapter(new TagAdapter<HotKeyResp>(data) {
            @Override
            public View getView(FlowLayout parent, int position, HotKeyResp hotKey) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.news_flowlayout_hotkey_item,
                        parent, false);
                assert hotKey != null;
                String name = hotKey.getName();
                tv.setText(name);
                return tv;
            }
        });

        flowLayoutHotKey.setOnTagClickListener((view, position, parent) -> {
            assert data.get(position) != null;
            gotoActivity(SearchInfoActivity.class, new KArticleReq(data.get(position).getName()));
            return true;
        });
    }

    @Override
    public void queryHotKeyFail(String errCode, String errMsg) {

    }

    @Override
    public void queryArticleSuccess(ArticleResp data) {

    }

    @Override
    public void queryArticleFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreArticleSuccess(ArticleResp data) {

    }

    @Override
    public void queryMoreArticleFail(String errCode, String errMsg) {

    }

    @OnClick({R2.id.iv_back, R2.id.iv_search, R2.id.iv_clear_history})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.iv_search) {
            if (!TextUtils.isEmpty(edtSearch.getText()))
                gotoActivity(SearchInfoActivity.class, new KArticleReq(edtSearch.getText().toString()));
        } else if (i == R.id.iv_clear_history) {

        }
    }
}

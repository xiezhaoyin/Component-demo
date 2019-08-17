package com.xzydonate.news.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xzydonate.basesdk.activity.BaseActivity;
import com.xzydonate.basesdk.widget.recyclerview.BaseQuickAdapter;
import com.xzydonate.basesdk.widget.recyclerview.BaseViewHolder;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;
import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.newsinfo.NewsInfoActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchInfoActivity extends BaseActivity implements ISearchView {
    @BindView(R2.id.iv_back)
    ImageButton ivBack;
    @BindView(R2.id.edt_search)
    EditText edtSearch;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.fab)
    FloatingActionButton fab;

    private KArticleReq kArticleReq = null;
    private SearchPresenter presenter = null;
    private int page = 0;
    private BaseQuickAdapter adapter = null;

    @Override
    public int createView() {
        return R.layout.activity_searchinfo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new SearchPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        setRefresh();
    }

    private void setRefresh() {
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 0;
            presenter.queryKeyArticle(kArticleReq);
//            refreshLayout.finishRefresh(1000);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
//            page++;
            presenter.queryMoreKeyArticle(page, kArticleReq);
//            refreshLayout.finishLoadMore(1000);
        });
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
                case "KArticleReq":
                    KArticleReq kArticleReq = (KArticleReq) event;
                    assert kArticleReq != null;
                    edtSearch.setText(kArticleReq.getK());
                    presenter.queryKeyArticle(kArticleReq);
                    break;
            }
            EventBus.getDefault().removeStickyEvent(event);
        }
    }

    @Override
    public void queryHotKeySuccess(List<HotKeyResp> data) {

    }

    @Override
    public void queryHotKeyFail(String errCode, String errMsg) {

    }

    @Override
    public void queryArticleSuccess(ArticleResp data) {
        page = 1;
        if (data.getDatas().size() <= 0)
            return;

        if (adapter == null) {
            adapter = new BaseQuickAdapter<ArticleResp.Article, BaseViewHolder>(R.layout.article_recycler_item, data.getDatas()) {
                @Override
                protected void convert(BaseViewHolder helper, final ArticleResp.Article item) {

                    String text = String.format("作者: %s   时间: %s", item.author, item.niceDate);

                    helper.setText(R.id.item_title, item.title);
                    helper.setText(R.id.item_subTitle, item.desc);
                    helper.setText(R.id.item_bottom, text);
                    helper.setText(R.id.item_star, item.zan + "");
                    helper.setOnClickListener(R.id.content, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoActivity(NewsInfoActivity.class, item);
                        }
                    });
                }
            };
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(data.getDatas());
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void queryArticleFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreArticleSuccess(ArticleResp data) {
        if (data.getDatas().size() > 0) {
            if (adapter != null) {
                adapter.addData(data.getDatas());
                page++;
            }
        }
        refreshLayout.finishLoadMore();
    }

    @Override
    public void queryMoreArticleFail(String errCode, String errMsg) {

    }

    @OnClick({R2.id.iv_back, R2.id.fab, R2.id.edt_search})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.fab) {

        } else if (i == R.id.edt_search) {
            gotoActivity(SearchActivity.class, null);
        }
    }
}

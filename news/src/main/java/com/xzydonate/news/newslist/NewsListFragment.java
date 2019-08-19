package com.xzydonate.news.newslist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzydonate.basesdk.activity.BaseFragment;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;
import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.newsinfo.NewsInfoActivity;
import com.xzydonate.news.project.ProjectResp;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements INewsListView {

    @BindView(R2.id.refreshLayout)
    SwipeRefreshLayout mSwipeRFLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.fab)
    FloatingActionButton fab;

    private NewsListPresenter presenter = null;
    private int page = 0;
    private BaseQuickAdapter adapter = null;

    @Override
    public int createView() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        presenter = new NewsListPresenter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mSwipeRFLayout.setProgressBackgroundColorSchemeResource(R.color.white);
        mSwipeRFLayout.setColorSchemeResources(R.color.colorPrimaryLight, R.color.colorPrimary, R.color.colorPrimaryDark);

        mSwipeRFLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRFLayout.setRefreshing(true);
                if (type == 0) {
                    presenter.queryArticle();
                } else {
                    presenter.queryProject();
                }
            }
        });

        mSwipeRFLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRFLayout.setRefreshing(true);
                if (type == 0) {
                    presenter.queryArticle();
                } else {
                    presenter.queryProject();
                }
            }
        });

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
    public void queryProjectSuccess(ProjectResp data) {
        page = 1;
        if (data.getDatas().size() <= 0)
            return;

        if (adapter == null) {
            adapter = new BaseQuickAdapter<ProjectResp.Project, BaseViewHolder>(R.layout.project_recycler_item, data.getDatas()) {
                @Override
                protected void convert(BaseViewHolder helper, final ProjectResp.Project item) {

                    String text = String.format("作者: %s   时间: %s", item.author, item.niceDate);

                    helper.setText(R.id.item_title, item.title);
                    helper.setText(R.id.item_subTitle, item.desc);
                    helper.setText(R.id.item_bottom, text);
                    helper.setText(R.id.item_star, item.zan + "");
                    Glide.with(NewsListFragment.this).load(item.envelopePic).into((ImageView) helper.getView(R.id.item_iv));
                }
            };

            adapter.setOnItemClickListener((adapter, view, position) -> gotoActivity(NewsInfoActivity.class, data.getDatas().get(position)));
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    presenter.queryMoreProject(page);
                }
            }, mRecyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(data.getDatas());
        }
        mSwipeRFLayout.setRefreshing(false);
    }

    @Override
    public void queryProjectFail(String errCode, String errMsg) {
        mSwipeRFLayout.setRefreshing(false);
    }

    @Override
    public void queryMoreProjectSuccess(ProjectResp data) {
        if (data.getDatas().size() > 0) {
            if (adapter != null) {
                adapter.addData(data.getDatas());
                adapter.loadMoreComplete();
                page++;
            }
        } else {
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void queryMoreProjectFail(String errCode, String errMsg) {
        if (adapter != null) {
            adapter.loadMoreFail();
        }
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
                }
            };

            adapter.setOnItemClickListener((adapter, view, position) -> gotoActivity(NewsInfoActivity.class, data.getDatas().get(position)));
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    presenter.queryMoreArticle(page);
                }
            }, mRecyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(data.getDatas());
        }
        mSwipeRFLayout.setRefreshing(false);
    }

    @Override
    public void queryArticleFail(String errCode, String errMsg) {
        mSwipeRFLayout.setRefreshing(false);
    }

    @Override
    public void queryMoreArticleSuccess(ArticleResp data) {
        if (data.getDatas().size() > 0) {
            if (adapter != null) {
                adapter.addData(data.getDatas());
                adapter.loadMoreComplete();
                page++;
            }
        } else {
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void queryMoreArticleFail(String errCode, String errMsg) {
        if (adapter != null) {
            adapter.loadMoreFail();
        }
    }


    @OnClick(R2.id.fab)
    public void onViewClicked() {
        mRecyclerView.smoothScrollToPosition(0);
    }
}

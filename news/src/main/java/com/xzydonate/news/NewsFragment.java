package com.xzydonate.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseFragment;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseQuickAdapter;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseViewHolder;
import com.xzydonate.basesdk.util.UrlRouter;
import com.xzydonate.news.newsInfo.NewsInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;

import static android.support.design.widget.TabLayout.OnTabSelectedListener;
import static android.support.design.widget.TabLayout.Tab;

@Route(path = UrlRouter.NEWS_FRAG)
public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {


    @BindView(R2.id.banner)
    Banner mBanner;
    @BindView(R2.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;


    private NewsPresenter presenter = null;
    private List<ProjectTreeResp> projectTreeRespList = null;
    private int cid = 0;
    private int page = 1;

    private BaseQuickAdapter adapter = null;

    @Override
    public int createView() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        presenter = new NewsPresenter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setBannerStyle(BannerConfig.RIGHT);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);

        presenter.queryBanner();
        presenter.queryProjectTree();
    }

    @Override
    public void resumeView() {
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void destroyView() {
        mBanner.stopAutoPlay();
        presenter.destroyPresenter();
    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @Override
    public void queryBannerSuccess(List<BannerResp> data) {
        Log.d(TAG, "queryBannerSuccess");
        if (data.size() <= 0)
            return;
        mBanner.setImages(data);
        mBanner.start();
    }

    @Override
    public void queryBannerFail(String errCode, String errMsg) {
        Log.d(TAG, "queryBannerFail");
    }

    @Override
    public void queryProjectTreeSuccess(List<ProjectTreeResp> data) {
        if (data.size() <= 0)
            return;

        projectTreeRespList = data;
        int i = 0;
        for (; i < data.size(); i++) {
            Tab tab = mTabLayout.newTab();
            tab.setText(data.get(i).getName());
            mTabLayout.addTab(tab);
        }

        cid = projectTreeRespList.get(0).getId();
        presenter.queryProject(new ProjectReq(cid));
        mTabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                cid = projectTreeRespList.get(tab.getPosition()).getId();
                presenter.queryProject(new ProjectReq(cid));
            }

            @Override
            public void onTabUnselected(Tab tab) {
            }

            @Override
            public void onTabReselected(Tab tab) {
            }
        });
    }

    @Override
    public void queryProjectTreeFail(String errCode, String errMsg) {

    }

    @Override
    public void queryProjectSuccess(ProjectResp data) {
        page = 2;
        if (data.getDatas().size() <= 0)
            return;

        if (adapter == null) {
            adapter = new BaseQuickAdapter<ProjectResp.Project, BaseViewHolder>(R.layout.news_recycler_item, data.getDatas()) {
                @Override
                protected void convert(BaseViewHolder helper, final ProjectResp.Project item) {

                    String text = String.format("作者：%s   时间：%s", item.author, item.niceDate);
                    int pos = text.indexOf("：");
                    int pos2 = text.lastIndexOf("：");
                    SpannableStringBuilder style = new SpannableStringBuilder(text);
//                        style.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(getContext(), 14)), 0, pos2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    style.setSpan(new ForegroundColorSpan(Color.parseColor("#3e3e3e")), pos, pos2 - 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    style.setSpan(new ForegroundColorSpan(Color.parseColor("#3e3e3e")), pos2, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                    helper.setText(R.id.title, item.title);
                    helper.setText(R.id.content, style);
                    helper.setOnClickListener(R.id.item, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gotoActivity(NewsInfoActivity.class, item);
                        }
                    });
                }
            };
            mRecyclerView.setAdapter(adapter);
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    presenter.queryMoreProject(page, new NewsReq(cid));
                }
            });
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        } else {
            adapter.setNewData(data.getDatas());
        }
    }

    @Override
    public void queryProjectFail(String errCode, String errMsg) {

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

}

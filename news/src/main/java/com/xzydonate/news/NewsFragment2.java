package com.xzydonate.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseEventFragment;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseQuickAdapter;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseViewHolder;
import com.xzydonate.basesdk.util.UrLRouter;
import com.xzydonate.news.newsInfo.NewsInfoActivity;

import java.util.List;

import butterknife.BindView;

@Route(path = UrLRouter.NEWS_FRAG)
public class NewsFragment2 extends BaseEventFragment implements INewsView{

    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    private NewsPresenter presenter = null;
    private int cid = 60;
    private int page = 2;
    private BaseQuickAdapter<NewsResp.NewsInfo, BaseViewHolder> adapter = null;

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.fragment_news2;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new NewsPresenter();
        presenter.createPresenter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        presenter.queryNews(new NewsReq(cid));
    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @Override
    public void queryBannerSuccess(List<BannerResp> data) {

    }

    @Override
    public void queryBannerFail(String errCode, String errMsg) {

    }

    @Override
    public void queryNewsSuccess(NewsResp data) {
        if (data.getDatas().size() > 0) {
            if (adapter == null) {
                adapter = new BaseQuickAdapter<NewsResp.NewsInfo, BaseViewHolder>(R.layout.news_recycler_item, data.getDatas()) {
                    @Override
                    protected void convert(BaseViewHolder helper, final NewsResp.NewsInfo item) {

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
                                gotoActivity(NewsInfoActivity.class,item);
                            }
                        });
                    }
                };
                mRecyclerView.setAdapter(adapter);

                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        presenter.queryMoreNews(page, new NewsReq(cid));
                    }
                });
                adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT_RIGHT);
            } else {
                adapter.setNewData(data.getDatas());
            }
        } else {

        }
    }

    @Override
    public void queryNewsFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreNewsSuccess(NewsResp data) {
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
    public void queryMoreNewsFail(String errCode, String errMsg) {
        if (adapter != null) {
            adapter.loadMoreFail();
        }
    }
}

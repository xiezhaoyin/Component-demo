package com.xzydonate.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.xzydonate.basesdk.activity.BaseFragment;
import com.xzydonate.basesdk.adapter.FragmentArgsAdapter;
import com.xzydonate.basesdk.router.UrlRouter;
import com.xzydonate.news.newslist.NewsListFragment;
import com.xzydonate.news.project.ProjectActivity;
import com.xzydonate.news.search.HotKeyResp;
import com.xzydonate.news.search.SearchActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = UrlRouter.NEWS_FRAG)
public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {

    @BindView(R2.id.banner)
    Banner mBanner;
    @BindView(R2.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;

    @BindView(R2.id.iv_icon)
    ImageButton ivIcon;
    @BindView(R2.id.iv_todo)
    ImageButton ivTodao;
    @BindView(R2.id.iv_star)
    ImageButton ivStar;
    @BindView(R2.id.edt_search)
    EditText edtSearch;

    @BindView(R2.id.tv_article)
    ImageView tvArticle;
    @BindView(R2.id.tv_project)
    ImageView tvProject;
    @BindView(R2.id.tv_navigation)
    ImageView tvNavigation;
    @BindView(R2.id.tv_publicode)
    ImageView tvPublicode;

    private NewsPresenter presenter = null;
    private FragmentArgsAdapter adapter = null;
    private Timer timer = null;

    @Override
    public int createView() {
        return R.layout.news_fragment_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new NewsPresenter(this);

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setBannerAnimation(Transformer.BackgroundToForeground);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);

        List<String> titles = new ArrayList<>();
        titles.add("最新博文");
        titles.add("最新项目");
        //初始化ViewPager的数据集
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(newInstance(new NewsListFragment(), 0));
        fragments.add(newInstance(new NewsListFragment(), 1));
        //创建ViewPager的adapter
        adapter = new FragmentArgsAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);

        presenter.queryBanner();
        presenter.queryHotKey();
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
        timer.cancel();
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
    public void queryHotKeySuccess(List<HotKeyResp> data) {
        if (data == null || data.size() <= 0)
            return;
        Random random = new Random();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtSearch.setHint(data.get(random.nextInt(data.size())).getName());
                    }
                });
            }
        }, 0, 5000);

    }

    @Override
    public void queryHotKeyFail(String errCode, String errMsg) {

    }

    @OnClick({R2.id.iv_icon, R2.id.iv_todo, R2.id.iv_star, R2.id.edt_search,
            R2.id.tv_article, R2.id.tv_project, R2.id.tv_navigation, R2.id.tv_publicode})
    public void onClick(View view) {
        Log.d(TAG, "click.id == " + view.getId());
        int i = view.getId();
        if (i == R.id.iv_icon) {
        } else if (i == R.id.iv_todo) {
        } else if (i == R.id.iv_star) {
        } else if (i == R.id.edt_search) {
            Log.d(TAG, "edt_search");
            gotoActivity(SearchActivity.class, null);
        } else if (i == R.id.tv_article) {
        } else if (i == R.id.tv_project) {
            gotoActivity(ProjectActivity.class, null);
        } else if (i == R.id.tv_navigation) {
        } else if (i == R.id.tv_publicode) {
        }
    }
}

package com.xzydonate.news.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.xzydonate.basesdk.activity.BaseActivity;
import com.xzydonate.news.R;
import com.xzydonate.news.R2;
import com.xzydonate.news.newsinfo.NewsInfoActivity;
import com.xzydonate.news.search.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ProjectActivity extends BaseActivity<ProjectPresenter> implements IProjectView {
    @BindView(R2.id.iv_back)
    ImageButton ivBack;
    @BindView(R2.id.edt_search)
    EditText edtSearch;
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.fab)
    FloatingActionButton fab;

    private ProjectPresenter presenter = null;
    private BaseQuickAdapter adapter = null;
    private int cid = 0;
    private int page = 0;

    @Override
    public int createView() {
        return R.layout.activity_project;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter = new ProjectPresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        setRefresh();

        presenter.queryProjectTree();
    }


    private void setRefresh() {
        // 设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        // 设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 0;
            presenter.queryProject(new ProjectReq(cid));
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.queryMoreProject(page, new ProjectReq(cid));
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

    }

    @Override
    public void queryProjectTreeSuccess(List<ProjectTreeResp> data) {
        if (data == null || data.size() <= 0)
            return;
        int i = 0;
        tabLayout.removeAllTabs();
        for (; i < data.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(data.get(i).getName());
            tabLayout.addTab(tab);
        }

        cid = data.get(0).getId();
        presenter.queryProject(new ProjectReq(cid));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                cid = data.get(tab.getPosition()).getId();
                presenter.queryProject(new ProjectReq(cid));
                recyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void queryProjectTreeFail(String errCode, String errMsg) {

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
                    Glide.with(ProjectActivity.this).load(item.envelopePic).into((ImageView) helper.getView(R.id.item_iv));
                }
            };

            adapter.setOnItemClickListener((adapter, view, position) -> gotoActivity(NewsInfoActivity.class, data.getDatas().get(position)));
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(data.getDatas());
        }

        refreshLayout.finishRefresh();
    }

    @Override
    public void queryProjectFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreProjectSuccess(ProjectResp data) {
        if (data.getDatas().size() > 0) {
            if (adapter != null) {
                adapter.addData(data.getDatas());
                page++;
            }
        }
        refreshLayout.finishLoadMore();
    }

    @Override
    public void queryMoreProjectFail(String errCode, String errMsg) {

    }

    @OnClick({R2.id.iv_back, R2.id.edt_search, R2.id.fab})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.fab) {
            recyclerView.smoothScrollToPosition(0);
        } else if (i == R.id.edt_search) {
            finish();
            gotoActivity(SearchActivity.class, null);
        }
    }
}

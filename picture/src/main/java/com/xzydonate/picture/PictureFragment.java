package com.xzydonate.picture;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.base.activity.BaseEventFragment;
import com.xzydonate.base.adapter.FragmentAdapter;
import com.xzydonate.base.util.UriRouter;
import com.xzydonate.picture.page1.PicturePage1Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = UriRouter.PICTURE_PICTURE_FRAG)
public class PictureFragment extends BaseEventFragment {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.tab)
    TabLayout mTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;

    private FragmentAdapter adapter = null;

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.fragment_picture;
    }

    @Override
    public void initView() {

        List<String> titles = new ArrayList<>();
        titles.add("ListView");
        titles.add("GridView");
        titles.add("瀑布流");
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PicturePage1Fragment());
        fragments.add(new PicturePage1Fragment());
        fragments.add(new PicturePage1Fragment());
        //创建ViewPager的adapter
        adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);

        //初始化ToolBar
        mToolbar.setTitle("MDProject");
        //导航图标
        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //程序logo
//        mToolbar.setLogo(anddro);

    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventType, Object event) {

    }
}

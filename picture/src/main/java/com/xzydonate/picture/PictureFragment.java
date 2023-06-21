package com.xzydonate.picture;

import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.xzydonate.basesdk.activity.BaseFragment;
import com.xzydonate.basesdk.adapter.FragmentAdapter;
import com.xzydonate.basesdk.router.UrlRouter;
import com.xzydonate.picture.page1.PicturePage1Fragment;
import com.xzydonate.picture.page2.PicturePage2Fragment;
import com.xzydonate.picture.page3.PicturePage3Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = UrlRouter.PICTURE_FRAG)
public class PictureFragment extends BaseFragment {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.tab)
    TabLayout mTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;

    private FragmentAdapter adapter = null;

    @Override
    public int createView() {
        return R.layout.picture_fragment_picture;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        List<String> titles = new ArrayList<>();
        titles.add("ListView");
        titles.add("瀑布流");
        titles.add("GridView");
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PicturePage1Fragment());
        fragments.add(new PicturePage2Fragment());
        fragments.add(new PicturePage3Fragment());
        //创建ViewPager的adapter
        adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(2);

        //初始化ToolBar
        mToolbar.setTitle("图片");
        mToolbar.setTitleTextColor(Color.WHITE);
        //导航图标
        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

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
}

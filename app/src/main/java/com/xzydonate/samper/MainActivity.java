package com.xzydonate.samper;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseActivity;
import com.xzydonate.basesdk.util.UrlRouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = UrlRouter.APP_HOME)
public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_view)
    FrameLayout mainView;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    // mainView
    private TabLayout tabLayout;
    private FrameLayout container;
    private FragmentManager fragmentManager = null;
    private ARouterFragManager aRouterFragManager = null;

    @Override
    public int createView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initMainMenuView();
        initLeftNavigationView();
        initBottomNavigationView();
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

    private void initBottomNavigationView() {
        List<String> titles = new ArrayList<>();
        titles.add("文章");
        titles.add("美图");
        titles.add("短视频");
        fragmentManager = getSupportFragmentManager();
        aRouterFragManager = ARouterFragManager.getInstance();
        aRouterFragManager.addFragment(fragmentManager, R.id.container, UrlRouter.NEWS_FRAG);
        aRouterFragManager.showOneFragment(fragmentManager, R.id.container, UrlRouter.NEWS_FRAG);

        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.tab_custom_view, null);
            ImageView img = view.findViewById(R.id.tab_img);
            TextView text = view.findViewById(R.id.tab_tag);
            TabLayout.Tab tab = tabLayout.newTab();
            switch (i) {
                case 0:
                    img.setBackgroundResource(R.drawable.selector_nav_news);
                    text.setText(titles.get(i));
                    break;
                case 1:
                    img.setBackgroundResource(R.drawable.selector_nav_picture);
                    text.setText(titles.get(i));
                    break;
                case 2:
                    img.setBackgroundResource(R.drawable.selector_nav_video);
                    text.setText(titles.get(i));
                    break;
            }

            tab.setCustomView(view);
            tabLayout.addTab(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                switch (index) {
                    case 0:
                        aRouterFragManager.addFragment(fragmentManager, R.id.container, UrlRouter.NEWS_FRAG);
                        aRouterFragManager.showOneFragment(fragmentManager, R.id.container, UrlRouter.NEWS_FRAG);
                        break;
                    case 1:
                        aRouterFragManager.addFragment(fragmentManager, R.id.container, UrlRouter.PICTURE_FRAG);
                        aRouterFragManager.showOneFragment(fragmentManager, R.id.container, UrlRouter.PICTURE_FRAG);
                        break;
                    case 2:
                        aRouterFragManager.addFragment(fragmentManager, R.id.container, UrlRouter.VIDEO_FRAG);
                        aRouterFragManager.showOneFragment(fragmentManager, R.id.container, UrlRouter.VIDEO_FRAG);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void initLeftNavigationView() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        //对NavigationView添加item的监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_detail:
                        navigationView.setCheckedItem(R.id.menu_detail);
                        break;
                    case R.id.menu_question:
                        navigationView.setCheckedItem(R.id.menu_question);
                        break;
                    case R.id.menu_share:
                        navigationView.setCheckedItem(R.id.menu_share);
                        break;
                }
                //关闭DrawerLayout回到主界面选中的tab的fragment页
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }

    private void initMainMenuView() {
        View main = LayoutInflater.from(this).inflate(R.layout.main_menu, mainView, true);
        tabLayout = main.findViewById(R.id.tab);
        container = main.findViewById(R.id.container);
    }
}

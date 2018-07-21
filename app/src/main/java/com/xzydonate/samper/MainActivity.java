package com.xzydonate.samper;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseEventActivity;
import com.xzydonate.basesdk.util.UrLRouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = UrLRouter.APP_HOME)
public class MainActivity extends BaseEventActivity {

    @BindView(R2.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R2.id.main_view)
    FrameLayout mainView;
    @BindView(R2.id.navigation_view)
    NavigationView navigationView;

    //mainView
    private TabLayout tabLayout;
    private FrameLayout container;

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initMainView();
        initNavigationView();
        setData();
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

    private void setData() {
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("统计");
        titles.add("我");
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragmentManager baseFragmentManager = BaseFragmentManager.getInstance();
        baseFragmentManager.addFragment(fragmentManager,R.id.container,UrLRouter.PICTURE_FRAG);
//        baseFragmentManager.addFragment(fragmentManager,R.id.container,UrLRouter.PICTURE_FRAG);
//        baseFragmentManager.addFragment(fragmentManager,R.id.container,UrLRouter.PICTURE_FRAG);
        baseFragmentManager.showOneFragment(fragmentManager,R.id.container,UrLRouter.PICTURE_FRAG);

        View view = LayoutInflater.from(this).inflate(R.layout.tab_custom_view, null);
        ImageView img = view.findViewById(R.id.tab_img);
        TextView text = view.findViewById(R.id.tab_tag);
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            switch (i) {
                case 0:
                    img.setBackgroundResource(R.drawable.selector);
                    text.setText(titles.get(i));
                    break;
                case 1:
                    img.setBackgroundResource(R.drawable.selector);
                    text.setText(titles.get(i));
                    break;
                case 2:
                    img.setBackgroundResource(R.drawable.selector);
                    text.setText(titles.get(i));
                    break;
            }

            tab.setCustomView(view);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initNavigationView() {
        //对NavigationView添加item的监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //点击NavigationView中定义的menu item时触发反应
                switch (item.getItemId()) {
                    case R.id.menu_info_details:
//                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_share:
//                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_agenda:
//                        viewPager.setCurrentItem(2);
                        break;
                }
                //关闭DrawerLayout回到主界面选中的tab的fragment页
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });


    }

    private void initMainView() {
        View main = LayoutInflater.from(this).inflate(R.layout.main_menu, mainView, true);
        tabLayout = main.findViewById(R.id.tab);
        container = main.findViewById(R.id.container);
    }
}

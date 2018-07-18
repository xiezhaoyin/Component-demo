package com.xzydonate.samper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.xzydonate.base.activity.BaseEventActivity;
import com.xzydonate.base.activity.LayoutResId;
import com.xzydonate.samper.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@LayoutResId(R.layout.activity_main)
public class MainActivity extends BaseEventActivity {

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_view)
    FrameLayout mainView;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    //mainView
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentAdapter adapter = null;

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initMainView();
        initLeftView();
        setData();
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

    private void setData() {
        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new ListFragment());
//        fragments.add(new NewsFragment());
//        fragments.add(new StagFragment());
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("统计");
        titles.add("我");

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
//                    d = getResources().getDrawable(R.drawable.selector);
                    break;
                case 1:
//                    d = getResources().getDrawable(R.drawable.selector);
                    break;
                case 2:
//                    d = getResources().getDrawable(R.drawable.selector);
                    break;
            }
            tab.setIcon(d);
        }

    }

    private void initLeftView() {
        //对NavigationView添加item的监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //点击NavigationView中定义的menu item时触发反应
                switch (item.getItemId()) {
                    case R.id.menu_info_details:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_share:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_agenda:
                        viewPager.setCurrentItem(2);
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
        tabLayout = (TabLayout) main.findViewById(R.id.tab);
        viewPager = (ViewPager) main.findViewById(R.id.vPager);
    }
}

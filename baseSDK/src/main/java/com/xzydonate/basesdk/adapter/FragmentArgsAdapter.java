package com.xzydonate.basesdk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.xzydonate.basesdk.activity.BaseFragment;

import java.util.List;

public class FragmentArgsAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    public FragmentArgsAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        fragment.updateArguments(position);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}

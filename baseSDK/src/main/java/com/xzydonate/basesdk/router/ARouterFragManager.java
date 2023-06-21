package com.xzydonate.basesdk.router;


import android.util.ArrayMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Iterator;

/**
 * Created by dell on 2018/3/16.
 */

public class ARouterFragManager {

    private ArrayMap<String, Fragment> fragArrayMap = null;
    private volatile static ARouterFragManager instance = null;

    private ARouterFragManager() {
        fragArrayMap = new ArrayMap<>();
    }

    public static ARouterFragManager getInstance() {
        if (instance == null) {
            synchronized (ARouterFragManager.class) {
                if (instance == null)
                    instance = new ARouterFragManager();
            }
        }
        return instance;
    }

    public Fragment getFragment(String fragPath) {
        return fragArrayMap.get(fragPath);
    }

    public void addFragment(FragmentManager fragmentManager, int fragResId, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragArrayMap.get(fragPath) == null) {
            newFragment(fragPath);
            transaction.add(fragResId, fragArrayMap.get(fragPath));
            transaction.commitAllowingStateLoss();
        }
    }

    public void removeFragment(FragmentManager fragmentManager, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragArrayMap.get(fragPath) != null) {
            fragArrayMap.get(fragPath).setUserVisibleHint(false);
            transaction.remove(fragArrayMap.get(fragPath));
            delFragment(fragPath);
        }
        transaction.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentManager fragmentManager, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragArrayMap.get(fragPath) != null) {
            fragArrayMap.get(fragPath).setUserVisibleHint(false);
            transaction.hide(fragArrayMap.get(fragPath));
        }
        transaction.commitAllowingStateLoss();
    }

    public void showFragment(FragmentManager fragmentManager, int resId, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragArrayMap.get(fragPath) != null) {
            transaction.show(fragArrayMap.get(fragPath));
            fragArrayMap.get(fragPath).setUserVisibleHint(true);
            transaction.commitAllowingStateLoss();
        } else {
            addFragment(fragmentManager, resId, fragPath);
        }
    }

    public void removeAllFragment(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Iterator iterator = fragArrayMap.keySet().iterator();
        String fragPath = null;
        while (iterator.hasNext()) {
            fragPath = (String) iterator.next();
            if (fragArrayMap.get(fragPath) != null) {
                transaction.remove(fragArrayMap.get(fragPath));
                delFragment(fragPath);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    public void hideAllFragment(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Iterator iterator = fragArrayMap.keySet().iterator();
        String fragPath  = null;
        while (iterator.hasNext()) {
            fragPath  = (String) iterator.next();
            if (fragArrayMap.get(fragPath ) != null) {
                transaction.hide(fragArrayMap.get(fragPath ));
            }
        }
        transaction.commitAllowingStateLoss();
    }

    public void showOneFragment(FragmentManager fragmentManager, int resId, String fragPath) {
        hideAllFragment(fragmentManager);
        showFragment(fragmentManager, resId, fragPath);
    }

    private void newFragment(String fragPath) {
        Fragment currFrag = (Fragment) ARouter.getInstance().build(fragPath).navigation();
        fragArrayMap.put(fragPath, currFrag);
    }

    private void delFragment(String fragPath) {
        fragArrayMap.remove(fragPath);
    }
}

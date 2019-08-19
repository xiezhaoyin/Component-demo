package com.xzydonate.samper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by dell on 2018/3/16.
 */

public class ARouterFragManager {

    private HashMap<String, Fragment> fragHashMap = null;
    private volatile static ARouterFragManager instance = null;

    private ARouterFragManager() {
        fragHashMap = new HashMap<>();
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
        return fragHashMap.get(fragPath);
    }

    public void addFragment(FragmentManager fragmentManager, int fragResId, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragHashMap.get(fragPath) == null) {
            newFragment(fragPath);
            transaction.add(fragResId, fragHashMap.get(fragPath));
            transaction.commitAllowingStateLoss();
        }
    }

    public void removeFragment(FragmentManager fragmentManager, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragHashMap.get(fragPath) != null) {
            fragHashMap.get(fragPath).setUserVisibleHint(false);
            transaction.remove(fragHashMap.get(fragPath));
            delFragment(fragPath);
        }
        transaction.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentManager fragmentManager, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragHashMap.get(fragPath) != null) {
            fragHashMap.get(fragPath).setUserVisibleHint(false);
            transaction.hide(fragHashMap.get(fragPath));
        }
        transaction.commitAllowingStateLoss();
    }

    public void showFragment(FragmentManager fragmentManager, int resId, String fragPath) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragHashMap.get(fragPath) != null) {
            transaction.show(fragHashMap.get(fragPath));
            fragHashMap.get(fragPath).setUserVisibleHint(true);
            transaction.commitAllowingStateLoss();
        } else {
            addFragment(fragmentManager, resId, fragPath);
        }
    }

    public void removeAllFragment(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Iterator iterator = fragHashMap.keySet().iterator();
        String fragPath = null;
        while (iterator.hasNext()) {
            fragPath = (String) iterator.next();
            if (fragHashMap.get(fragPath) != null) {
                transaction.remove(fragHashMap.get(fragPath));
                delFragment(fragPath);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    public void hideAllFragment(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Iterator iterator = fragHashMap.keySet().iterator();
        String fragPath  = null;
        while (iterator.hasNext()) {
            fragPath  = (String) iterator.next();
            if (fragHashMap.get(fragPath ) != null) {
                transaction.hide(fragHashMap.get(fragPath ));
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
        fragHashMap.put(fragPath, currFrag);
    }

    private void delFragment(String fragPath) {
        fragHashMap.remove(fragPath);
    }
}

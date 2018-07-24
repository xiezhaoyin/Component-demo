//package com.xzydonate.basesdk;
//
//import android.app.Application;
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ApplicationDelegate implements IAppLife {
//    private List<IModuleConfig> list;
//    private List<IAppLife> appLifes;
//    private List<Application.ActivityLifecycleCallbacks> liferecycleCallbacks;
//
//    public ApplicationDelegate() {
//        appLifes = new ArrayList<>();
//        liferecycleCallbacks = new ArrayList<>();
//    }
//
//    @Override
//    public void attachBaseContext(Context base) {
//        //初始化Manifest文件解析器，用于解析组件在自己的Manifest文件配置的Application
//        ManifestParser manifestParser = new ManifestParser(base);
//        list = manifestParser.parse();
//        //解析得到的组件Application列表之后，给每个组件Application注入
//        //context，和Application的生命周期的回调，用于实现application的同步
//        if (list != null && list.size() > 0) {
//            for (IModuleConfig configModule :
//                    list) {
//                configModule.injectAppLifecycle(base, appLifes);
//                configModule.injectActivityLifecycle(base, liferecycleCallbacks);
//            }
//        }
//        if (appLifes != null && appLifes.size() > 0) {
//            for (IAppLife life :
//                    appLifes) {
//                life.attachBaseContext(base);
//            }
//        }
//    }
//
//    @Override
//    public void onCreate(Application application) {
//        //相应调用组件Application代理类的onCreate方法
//        if (appLifes != null && appLifes.size() > 0) {
//            for (IAppLife life :
//                    appLifes) {
//                life.onCreate(application);
//            }
//        }
//        if (liferecycleCallbacks != null && liferecycleCallbacks.size() > 0) {
//            for (Application.ActivityLifecycleCallbacks life :
//                    liferecycleCallbacks) {
//                application.registerActivityLifecycleCallbacks(life);
//            }
//        }
//    }
//
//    @Override
//    public void onTerminate(Application application) {
//        //相应调用组件Application代理类的onTerminate方法
//        if (appLifes != null && appLifes.size() > 0) {
//            for (IAppLife life :
//                    appLifes) {
//                life.onTerminate(application);
//            }
//        }
//        if (liferecycleCallbacks != null && liferecycleCallbacks.size() > 0) {
//            for (Application.ActivityLifecycleCallbacks life :
//                    liferecycleCallbacks) {
//                application.unregisterActivityLifecycleCallbacks(life);
//            }
//        }
//    }
//}
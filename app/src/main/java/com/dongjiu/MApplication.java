package com.dongjiu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Created by aaron on 16/9/7.
 * MultiDexApplication  // 方法超过64 的的时候需要添加的限制
 *
 */

public class MApplication extends MultiDexApplication {
    private static ArrayList<Activity> activities = new ArrayList<Activity>();
    private String name;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //ZXingLibrary.initDisplayOpinion(this);

        // 在 Application 中初始化
       // ToastUtils.init(this);
        //创建全局变量，在不同的activity可直接引用即可；
        //String appName = getApplicationContext().getResources().getString(R.string.app_name);
        //返回当前进程的名称
       // String processName = getProcessName();
        mContext  = getApplicationContext();
        ;
        initLogger();
    }


    public static Context getContext() {
        return mContext;
    }

    private void initLogger() {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
////                .showThreadInfo(false)  // 是否显示线程信息 默认显示 上图Thread Infrom的位置
////                .methodCount(0)         // 展示方法的行数 默认是2  上图Method的行数
////                .methodOffset(7)        // 内部方法调用向上偏移的行数 默认是0
//////                .logStrategy(customLog) // 改变log打印的策略一种是写本地，一种是logcat显示 默认是后者（当然也可以自己定义）
////                .tag("mvp_network_tag")   // 自定义全局tag 默认：PRETTY_LOGGER
////                .build();
////        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
////            @Override
////            public boolean isLoggable(int priority, String tag) {
////                return true;
////            }
////        });
    }



    /*
     * 当操作系统确定是进程从其进程中删除不需要的内存的好时机时调用;
     * 例如，当它进入后台并且没有足够的内存来保持尽可能多的后台进程运行时，就会发生这种情况;
     * 通常比较该值是否大于或等于
     */
//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        switch (level) {
//            case TRIM_MEMORY_UI_HIDDEN:
//                break;
//        }
//    }

    /*
     *当组件运行时，设备配置发生变化时由系统调用，如：屏幕旋转，通过重新检索资源
     */
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//    }

    /*
     *当整个系统内存不足时调用此方法，也可采用手动方式重写此方法来清空缓存或者释放不必要的资源
     */
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//    }

    /*
     * 应用程序结束时调用，
     */
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//    }

    /*
     * 关闭Application finish activity 并释放内存；
     */
//    public static void exitApp() {
//        for (Activity acti : activities) {
//            acti.finish();
//        }
//        System.exit(0);
//    }

    /*
     *添加相应的activity
     */
//    public static void addActivity(Activity acti) {
//        activities.add(acti);
//    }

    /*
     *finish相应的activity
     */
//    public static void removeActivity(Activity acti) {
//        int index = -1;
//        if ((index = activities.indexOf(acti)) != -1) {
//            activities.remove(index).finish();
//        }
//    }


//    protected void setName(String name) {
//        this.name = name;
//    }
//
//    protected String getName() {
//        return name;
//    }



//    @Override
//    protected void attachBaseContext(android.content.Context base) {  //或者使用这种方法解决引用方法限制的问题
//        super.attachBaseContext(base);
//        android.support.multidex.MultiDex.install(this);
//    }

}

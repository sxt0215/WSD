package com.jyph.wsdapp.common.application;


import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.gdkj.pushlibrary.GDJPushManager;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.common.utils.LogMe;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Created by sxt_0 on 2017/8/11.
 */

public class MyApplicationLike extends DefaultApplicationLike implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "Tinker.MyApplicationLike";

    private UserInfo userInfo;
    private MySharePreference sharePreference;
    public static MyApplicationLike mInstance;


    public MyApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        /**
         * Bugly 腾讯  收集crash日志
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         * 输出详细的Bugly SDK的Log；
         * 每一条Crash都会被立即上报；
         * 自定义日志将会在Logcat中输出。
         * 建议在测试阶段建议设置成true，发布时设置为false。
         * */
        Bugly.init(getApplication(), "900029763", true);

        sharePreference = MySharePreference.newInstance(getApplication());
        userInfo = new UserInfo(sharePreference.getUserName(sharePreference.getUserId()), sharePreference.getUserId(), sharePreference.getMobile(), sharePreference.getToken(), sharePreference.getAccountStatus(), sharePreference.getUserEmail());
        LogMe.e("application", "userName:" + sharePreference.getUserName(sharePreference.getUserId()));
        LogMe.e("application", "userId:" + sharePreference.getUserId());
        LogMe.e("application", "userMobile:" + sharePreference.getMobile());
        LogMe.e("application", "token:" + sharePreference.getToken());
        LogMe.e("application", "accountStatus:" + sharePreference.getAccountStatus());
        LogMe.e("application", "userEmail:" + sharePreference.getUserEmail());
        //极光推送
        GDJPushManager.getInstance().init(getApplication(),true);
        GDJPushManager.getInstance().setAlias("WSD");

        mInstance = this;

    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
    //=========Appliaction 中定义的内容===========

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("MyApplication", "Memory is low!!!");
    }
    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized MyApplicationLike getInstance() {
        return mInstance;
    }

    public MySharePreference getSharePreference() {
        return sharePreference;
    }

    public void setSharePreference(MySharePreference sharePreference) {
        this.sharePreference = sharePreference;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private boolean isDownload;

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean isDownload) {
        this.isDownload = isDownload;
    }

    //-------------------------记录崩溃日志  welcome---------------
    public void initCrash() {
        if (LogMe.isSaveCrashInfo)
            Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        LogMe.e("AppCrash", ex);
        //退出程序
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }
    //-------------------------记录崩溃日志  end---------------


}

package com.jyph.wsdapp.common.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.gdkj.pushlibrary.GDJPushManager;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.common.utils.LogMe;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by creditcloud on 7/26/16.
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    private UserInfo userInfo;
    private MySharePreference sharePreference;
    private boolean isGestureOff = false;//表示手势密码开启

//    {
//        PlatformConfig.setWeixin("wxd5b2fed68bd2e2c3", "aa1beaea7f304617266f840a2814d109");
//        PlatformConfig.setQQZone("1106087692", "HNt8t9xJ2KC62OKg");
//        PlatformConfig.setSinaWeibo("4279721925", "4b2abd4513867841c24311ffb48228ff", "http://sns.whalecloud.com/sina2/callback");
//    }

    public boolean isGestureOff() {
        return isGestureOff;
    }

    public void setGestureOff(boolean gestureOff) {
        isGestureOff = gestureOff;
    }

    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
//        UMShareAPI.get(this);
//        LeakCanary.install(this);
        sharePreference = MySharePreference.newInstance(getApplicationContext());
        userInfo = new UserInfo(sharePreference.getUserName(sharePreference.getUserId()), sharePreference.getUserId(), sharePreference.getMobile(), sharePreference.getToken(), sharePreference.getAccountStatus(), sharePreference.getUserEmail());
        LogMe.e("application", "userName:" + sharePreference.getUserName(sharePreference.getUserId()));
        LogMe.e("application", "userId:" + sharePreference.getUserId());
        LogMe.e("application", "userMobile:" + sharePreference.getMobile());
        LogMe.e("application", "token:" + sharePreference.getToken());
        LogMe.e("application", "accountStatus:" + sharePreference.getAccountStatus());
        LogMe.e("application", "userEmail:" + sharePreference.getUserEmail());

        GDJPushManager.getInstance().init(this,true);
        GDJPushManager.getInstance().setAlias("WSD");
        /**
         * Bugly 腾讯  收集crash日志
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         * 输出详细的Bugly SDK的Log；
         * 每一条Crash都会被立即上报；
         * 自定义日志将会在Logcat中输出。
         * 建议在测试阶段建议设置成true，发布时设置为false。
         * */
        CrashReport.initCrashReport(getApplicationContext(), "f0f87f9764", true);
        //通过“AndroidManifest.xml”配置后的初始化方法如下：
        CrashReport.initCrashReport(getApplicationContext());
//        CrashReport.testJavaCrash();
        mInstance = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Multidex.install(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("MyApplication", "Memory is low!!!");
    }

    /**
     * 支持 multidex
     * */
//    @Override
//    protected void attachBaseContext(Context base) {
//        MultiDex.install(this);
//        super.attachBaseContext(base);
//    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized MyApplication getInstance() {
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

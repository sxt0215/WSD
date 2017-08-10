package com.jyph.wsdapp.common.application;

import android.app.Application;
import android.util.Log;

import com.gdkj.pushlibrary.GDJPushManager;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.common.utils.LogMe;



/**
 * Created by creditcloud on 7/26/16.
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "JIGUANG-Example";
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

        GDJPushManager.getInstance().init(this,true);
        GDJPushManager.getInstance().setAlias("WSD");

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
        mInstance = this;
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

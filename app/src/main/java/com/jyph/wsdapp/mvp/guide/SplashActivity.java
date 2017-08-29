package com.jyph.wsdapp.mvp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jyph.wsdapp.mvp.view.activity.MainActivity;
import com.umeng.analytics.MobclickAgent;
import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.mvp.view.activity.MainTestActivity;


public class SplashActivity extends BaseActivity {

    private View view;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_splash, null);
        //上传日志开启加密
        //AnalyticsConfig.enableEncrypt(true);
        //不使用自动Activity设置
        MobclickAgent.openActivityDurationTrack(false);
        //集成测试
        MobclickAgent.setDebugMode(true);
        //在线参数
        //MobclickAgent.updateOnlineConfig(this);
        setContentView(view);
        //into();
        getAllInfomation();

    }

    private void getAllInfomation() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//2s
                    startAc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    startAc();
                }
            }
        }).start();
    }


    private void startAc() {
        final boolean isfirstin = MySharePreference.isFirstIn(SplashActivity.this);
        if (!isfirstin) {
            startActivity(new Intent(this, GuideActivity.class));//引导页
            getSharedPref().setFirstLaunch();
        }
//        else if (getSharedPref().isLogin()) {//判断用户是否登录
//            if (TextUtils.isEmpty(getSharedPref().getGesture(getSharedPref().getUserId()))) {//如果手势密码为空，则进到手势密码设置页面
//                LogMe.e("手势密码的开关==isGestureOff", getSharedPref().isGestureOpen()+"");
////                if (!getSharedPref().isGestureOpen()) {//为空的话，还要判断下用户是否关闭了手势密码  false  关闭了
//                    startActivity(new Intent(this, MainTestActivity.class));
////                } else {
////                    startActivity(new Intent(this, GestureRegisterActivity.class));
////                }
//            } else {
//                startActivity(new Intent(this, MainTestActivity.class));
//                //手势密码
////                startActivity(new Intent(this, GestureBackActivity.class));
//            }
//        }
        else {
//            startActivity(new Intent(this, MainTestActivity.class));
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }


    // 进入主程序的方法
    public void into() {

        // 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        // 给view设置动画效果
        view.startAnimation(animation);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;

    }

}

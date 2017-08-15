package com.jyph.wsdapp.basemvp.view.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;
import com.jyph.wsdapp.basemvp.view.impl.MvpBaseActivity;
import com.jyph.wsdapp.common.application.MyApplication;
import com.jyph.wsdapp.common.application.MyApplicationLike;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.common.utils.LogMe;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


/**
 * Created by sxt on 16/12/15.
 */
public class BaseActivity<P extends MvpPresenter> extends MvpBaseActivity<P> {
    String IP3g, IPwifi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMe.d("onCreate====>>","onCreate");
//        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LogMe.d("onStart====>>","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogMe.d("onResume====>>","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogMe.d("onPause====>>","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogMe.d("onStop====>>","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogMe.d("onDestroy====>>","onDestroy");
        super.onDestroy();
//        ActivityCollector.removeActivity(this);

    }

//    @Override
//    public P bindPresenter() {
//        return null;
//    }
//
//    public Toolbar initToolbarAsHome() {
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//
//        }
//        return toolbar;
//    }

//    public Toolbar initToolbar(String title) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(title);
//        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//
//        }
//        return toolbar;
//    }

    public void showSnackbar(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);

        }

        return true;
    }

    public void logout(Context context) {
        getSharedPref().setGesture("", getSharedPref().getUserId());
        LogMe.d("是否删除",getSharedPref().getGesture(getSharedPref().getUserId()));
        getSharedPref().setLogin(false, "", "", "", "", 0,"");
        //不要修改
//        Intent intent = new Intent(context, CheckMobileActivity.class);
//        startActivity(intent);
        //不要修改
//        ActivityCollector.finishAllExceptCheckMobile();
    }

    public MySharePreference getSharedPref() {
        return MyApplicationLike.getInstance().getSharePreference();
    }

    public void showSystemShortToast(String msg) {
//        if (!OnClickUtil.isFastDoubleClick(2000)) {
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        }
    }

    public void showSystemToast(String msg) {
        //if (!OnClickUtil.isFastDoubleClick(2000)) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //}
    }

    //处理bar的返回键
    public void finishActivity(View view){
        finish();
    }

    public String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        IP3g = inetAddress.getHostAddress().toString();
                        return IP3g;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private String wifi() {
//        //获取wifi服务
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        //判断wifi是否开启
//        if (!wifiManager.isWifiEnabled()) {
//            wifiManager.setWifiEnabled(true);
//        }
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        int ipAddress = wifiInfo.getIpAddress();
//        IPwifi = intToIp(ipAddress);
//        return IPwifi;
//    }

    private String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    //ip地址获取
    public String getIP() {
        if (getIpAddress() == null || "".equals(getIpAddress())) {
            return IP3g;
        } else {
//            wifi();
            return IPwifi;
        }
    }

    @Override
    public P bindPresenter() {
        return null;
    }

//    public Dialog progressDialog;
//
//    public void showProgress(String strMsg) {
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//
//        progressDialog = new Dialog(this, R.style.progress_dialog);
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.setCancelable(true);
//        progressDialog.getWindow().setBackgroundDrawableResource(
//                android.R.color.transparent);
//        TextView msg = (TextView) progressDialog
//                .findViewById(R.id.id_tv_loadingmsg);
//        if (strMsg.isEmpty())
//            msg.setText("正在加载");
//        else
//            msg.setText(strMsg);
//        progressDialog.show();
//        LogMe.e("Progress","welcome");
//    }

//    public void cancelProgress() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//            progressDialog = null;
//            LogMe.e("Progress","end");
//        }
//    }
//
//    //提示用户绑卡
//    private PrompfDialog prompfBindDialog;
//    public void showBindCardDialog(final Context context){
//        if(prompfBindDialog == null){
//            prompfBindDialog = new PrompfDialog(context,R.style.transparentFrameWindowStyle,"确  定","取  消",
//                    "您还未绑定银行卡，请先去绑定银行卡","");
//            prompfBindDialog.setCancelable(false);
//            prompfBindDialog.setUpdateOnClickListener(new PrompfDialog.UpdateOnclickListener() {
//                @Override
//                public void dismiss() {
//
//                }
//
//                @Override
//                public void BtnYesOnClickListener(View v) {
//                    //进到绑卡页面
//                    startActivity(new Intent(context,AddBankCardActivity.class));
//                }
//
//                @Override
//                public void BtnCancleOnClickListener(View v) {
//                    finish();
////                    prompfDialog.dismiss();
//                }
//            });
//            Window window = prompfBindDialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            prompfBindDialog.show();
//        }else{
//            prompfBindDialog.show();
//        }
//    }

//    private PrompfDialog prompfForgetDialog;
//    public void showForgetGesturDialog(final Context context){
//        if(prompfForgetDialog == null){
//            prompfForgetDialog = new PrompfDialog(context,R.style.transparentFrameWindowStyle,"确  定","取  消",
//                    "登录成功后请重新设置手势密码","");
//            prompfForgetDialog.setCancelable(false);
//            prompfForgetDialog.setUpdateOnClickListener(new PrompfDialog.UpdateOnclickListener() {
//                @Override
//                public void dismiss() {
//
//                }
//
//                @Override
//                public void BtnYesOnClickListener(View v) {
//                    //退出
//                    logout(context);
//                }
//
//                @Override
//                public void BtnCancleOnClickListener(View v) {
//                    prompfForgetDialog.dismiss();
//                }
//            });
//            Window window = prompfForgetDialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            prompfForgetDialog.show();
//        }else{
//            prompfForgetDialog.show();
//        }
//    }

//    //提示用户充值
//    private PrompfDialog prompfRechargeDialog;
//    public void showRechargeDialog(final Context context){
//        if(prompfRechargeDialog == null){
//            prompfRechargeDialog = new PrompfDialog(context,R.style.transparentFrameWindowStyle,"充  值","取  消",
//                    "账户余额不足,请充值","");
//            prompfRechargeDialog.setCancelable(false);
//            prompfRechargeDialog.setUpdateOnClickListener(new PrompfDialog.UpdateOnclickListener() {
//                @Override
//                public void dismiss() {
//
//                }
//
//                @Override
//                public void BtnYesOnClickListener(View v) {
//                    //充值
//                    RechargeWithdrawActivity.launch(BaseActivity.this, RechargeWithdrawActivity.MODE_RECHARGE);
//                }
//
//                @Override
//                public void BtnCancleOnClickListener(View v) {
//                    prompfRechargeDialog.dismiss();
//                }
//            });
//            Window window = prompfRechargeDialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            prompfRechargeDialog.show();
//        }else{
//            prompfRechargeDialog.show();
//        }
//    }
//
//    public final int getPhoneWidth(){
//
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
//
//        return width;
//    }
//
//    public UMShareListener umShareListener = new UMShareListener() {
//        @Override
//        public void onStart(SHARE_MEDIA share_media) {
//            //分享开始的回到
//            LogMe.d("plat", "onStart" + share_media);
//
//        }
//
//        @Override
//        public void onResult(SHARE_MEDIA share_media) {
//            LogMe.d("plat", "platform" + share_media);
//            Toast.makeText(BaseActivity.this, share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//            Toast.makeText(BaseActivity.this, share_media + " 分享失败啦", Toast.LENGTH_SHORT).show();
//            if (throwable != null) {
//                LogMe.d("throw", "throw:" + throwable.getMessage());
//                Toast.makeText(BaseActivity.this, share_media + throwable.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA share_media) {
//            LogMe.d("plat", "onCancel" + share_media);
//
////            Toast.makeText(BaseActivity.this, share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
//
//        }
//    };


}

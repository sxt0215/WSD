package com.jyph.wsdapp.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.view.ClearEditText;
import com.jyph.wsdapp.mvp.Presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by sxt_0 on 2017/8/2.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.cutline)
    View cutline;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.et_login_account)
    ClearEditText etLoginAccount;
    @BindViews({R.id.et_img_code, R.id.et_sms_code})
    EditText etImgCode, etSmsCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindViews({R.id.btn_get_code, R.id.tv_phone, R.id.tv_use_agree})
    TextView btnGetCode, tvPhone, tvUseAgree;

    private CountDownTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_left, R.id.btn_login, R.id.btn_get_code, R.id.tv_phone, R.id.tv_use_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left://暂定跳到首页（后期从哪来回哪去）
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.btn_login://登陆  暂定跳到信息完善页面（后期从哪来回哪去）
                startActivity(new Intent(this, BorrowInfoGuideActivity.class));
                break;
            case R.id.btn_get_code://点击发送短信验证码 60s 倒计时
                getPresenter().getRegisterSmsCode("手机号");
                break;
            case R.id.tv_phone://点击进行电话播报密码  倒计时开始
                break;
            case R.id.tv_use_agree://用户协议页面
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (task != null && !task.isCancelled())
            task.cancel(true);
    }

    public void startCounting() {
        task = new CountDownTask();
        task.execute();
    }

    /**
     * 60s 倒计时
     */
    public class CountDownTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            Log.e("TASk", "preexecute");
            btnGetCode.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            btnGetCode.setEnabled(true);
            btnGetCode.setText(getResources().getString(R.string.get_code));
            Log.e("TASk", "postexecute");
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("TASk", "doinbackground");
            for (int i = 60; i >= 0; i--) {
                try {
                    System.out.println("---------" + i);
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e("TASk", "onprogressupdate");
            btnGetCode.setText(values[0] + "秒");
            super.onProgressUpdate(values);
        }
    }





}

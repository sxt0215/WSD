package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.view.ClearEditText;
import com.jyph.wsdapp.common.utils.view.TextImageButton;
import com.jyph.wsdapp.mvp.presenter.LoginPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by sxt_0 on 2017/8/2.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.et_login_account)
    ClearEditText etLoginAccount;
    @BindViews({R.id.et_img_code, R.id.et_sms_code})
    List<EditText> codes;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindViews({R.id.btn_get_code, R.id.tv_phone, R.id.tv_use_agree})
    List<TextView> btnGetCode;//, tvPhone, tvUseAgree
    @BindView(R.id.tib_code)
    TextImageButton tibCode;
    private CountDownTask task;
    private String useMobile, imgCode, smsCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.img_left, R.id.btn_login, R.id.btn_get_code, R.id.tv_phone, R.id.tv_use_agree,R.id.tib_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left://暂定跳到首页（后期从哪来回哪去）
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_login://登陆  暂定跳到信息完善页面（后期从哪来回哪去）
                getPresenter().getLogin(etLoginAccount.getText().toString(), codes.get(0).getText().toString(), codes.get(1).getText().toString());
                break;
            case R.id.btn_get_code://点击发送短信验证码 60s 倒计时
//                getPresenter().getRegisterSmsCode("手机号");
                startCounting();
                break;
            case R.id.tv_phone://点击进行电话播报密码  倒计时开始
                showSystemShortToast("接听电话并开始倒计时");
                startCounting();
                break;
            case R.id.tv_use_agree://用户协议页面
                showSystemShortToast("进到用户协议页面");
                setProtocol(btnGetCode.get(2), getString(R.string.agreement));
                break;
            case R.id.tib_code:
                //发送获取验证码请求

                break;
        }
    }

    public void jump() {
        startActivity(new Intent(this, BorrowInfoGuideActivity.class));
        finish();
    }

    public void toastInfo(String msg) {
        showSystemShortToast(msg);
    }

    @Override
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
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
            btnGetCode.get(0).setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            btnGetCode.get(0).setEnabled(true);
            btnGetCode.get(0).setText(getResources().getString(R.string.get_code));
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
            btnGetCode.get(0).setText(values[0] + "s");
            super.onProgressUpdate(values);
        }
    }

    /**
     * 设置协议跳转
     *
     * @param tv
     * @param protocol
     */
    private void setProtocol(TextView tv, String protocol) {
        final String prefix = getString(R.string.agreement_explain);
        int prefixLength = prefix.length();
//        String textProtocol = prefix + String.format("《%1$s》", protocol);
        String textProtocol = String.format("《%1$s》", protocol);
        SpannableStringBuilder style = new SpannableStringBuilder(textProtocol);
        TextViewURLSpan myURLSpan = new TextViewURLSpan();
        style.setSpan(myURLSpan, prefixLength, prefixLength + protocol.length() + 2,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(style);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private class TextViewURLSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.black_5a5a5a));
            ds.setUnderlineText(false); // 去掉下划线
        }

        @Override
        public void onClick(View widget) {// 点击事件 Intent intent=new
            String item = getString(R.string.agreement);
            WebViewActivity.startAc(getApplicationContext(), item, WebViewActivity.CMS_URL_PROTOCOL, getString(R.string.agreement), "", "");
        }
    }


}

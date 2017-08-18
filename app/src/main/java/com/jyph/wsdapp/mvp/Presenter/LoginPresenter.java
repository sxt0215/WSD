package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.common.application.MyApplication;
import com.jyph.wsdapp.common.application.MyApplicationLike;
import com.jyph.wsdapp.common.bean.BaseInfo;
import com.jyph.wsdapp.common.bean.LoginInfo;
import com.jyph.wsdapp.common.network.ApiException;
import com.jyph.wsdapp.common.rxjava.MyObserver;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.mvp.model.LoginModel;
import com.jyph.wsdapp.mvp.view.activity.LoginActivity;

/**
 * Created by sxt_0 on 2017/8/7.
 */

public class LoginPresenter extends BasePresenter<LoginActivity,LoginModel> {
    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public LoginModel bindModel() {
        return new LoginModel(getContext());
    }

    //获取短信验证码
    public void getRegisterSmsCode(String moblie,String imgCode) {
        if(TextUtils.isEmpty(moblie)){
            getView().toastInfo("手机号不能为空");
        }
        if(TextUtils.isEmpty(imgCode)){
            getView().toastInfo("图形验证码不能为空");
        }
        getModel().getSmsCode(moblie).subscribe(getSmsCode());
    }
    //登录
    public void getLogin(String userMobile,String imgCode,String code){
        if(TextUtils.isEmpty(userMobile)){
            getView().toastInfo("手机号不能为空");
        }
        if(TextUtils.isEmpty(imgCode)){
            getView().toastInfo("图形验证码不能为空");
        }
        if (TextUtils.isEmpty(code)){
            getView().toastInfo("短信验证码不能为空");
        }
        getModel().goToLogin(userMobile,code).subscribe(getLogin());
    }

    public MyObserver<LoginInfo> getLogin(){
        MyObserver<LoginInfo> myObserver = new MyObserver<LoginInfo>() {
            @Override
            public void onNext(LoginInfo value) {
                if (value !=null){
                    if(value.isSuccess()){
                        getView().toastInfo("登录成功");
                        getView().jump();//
                        //保存下用户信息 方便后面使用
                        String userName = value.getUserInfo().getUserNmae();
                        MySharePreference mSharePreference = MyApplicationLike.getInstance().getSharePreference();
                        if (TextUtils.isEmpty(userName)) {
                            userName = "";
                            MyApplicationLike.getInstance().getSharePreference().setKaihu(false);
                        } else {
                            try {
                                userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            mSharePreference.setKaihu(true);
                        }
                        String userId = value.getUserInfo().getUserId();
                        String mobile = value.getUserInfo().getUserMobile();
                        boolean emergency = value.getUserInfo().isEmergency();
                        boolean identity = value.getUserInfo().isIdentity();
                        boolean otherInfo = value.getUserInfo().isOtherInfo();
                        boolean undetermined = value.getUserInfo().isUndetermined();
                        mSharePreference.setEmergency(emergency);
                        mSharePreference.setIdentity(identity);
                        mSharePreference.setOtherInfo(otherInfo);
                        mSharePreference.setUndetermined(undetermined);
                        mSharePreference.setLogin(true, userName, userId, mobile, "", 0, "");
                        mSharePreference.setUserName(value.getUserInfo().getUserNmae(), userId);
                        LogMe.e("姓名", MyApplicationLike.getInstance().getSharePreference().getUserName(userId));
                        LogMe.e("是否登录", mSharePreference.isLogin() + "");
                    }else {
                        getView().toastInfo("登录失败，请重试");
                        getView().jump();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onApiError(ApiException exception) {
                super.onApiError(exception);
            }
        };
        return myObserver;
    }

    public MyObserver<BaseInfo> getSmsCode() {
        MyObserver<BaseInfo> myObserver = new MyObserver<BaseInfo>(this) {
            @Override
            public void onNext(BaseInfo value) {
                if (value != null) {
                    if (value.isSuccess()) {
                        getView().startCounting();
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                LogMe.d("error", e + "");
            }
            @Override
            public void onApiError(ApiException exception) {
                super.onApiError(exception);
            }
        };
        return myObserver;
    }








}

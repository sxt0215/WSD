package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.common.bean.BaseInfo;
import com.jyph.wsdapp.common.bean.LoginInfo;
import com.jyph.wsdapp.common.network.ApiException;
import com.jyph.wsdapp.common.rxjava.MyObserver;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.mvp.model.HomeModel;
import com.jyph.wsdapp.mvp.model.LoginModel;
import com.jyph.wsdapp.mvp.view.activity.HomeActivity;
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
                    }else {
                        getView().toastInfo("登录失败，请重试");
//                        getView().jump();//
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

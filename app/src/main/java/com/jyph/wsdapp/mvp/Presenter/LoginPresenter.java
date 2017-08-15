package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.common.bean.BaseInfo;
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
    public void getRegisterSmsCode(String moblie) {
        getModel().getSmsCode(moblie).subscribe(getSmsCode());
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

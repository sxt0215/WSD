package com.jyph.wsdapp.mvp.model;

import android.content.Context;

import com.jyph.wsdapp.basemvp.model.base.BaseModel;
import com.jyph.wsdapp.common.bean.BaseInfo;
import com.jyph.wsdapp.common.bean.LoginInfo;
import com.jyph.wsdapp.common.network.Api;
import com.jyph.wsdapp.mvp.wsdapi.MyApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by sxt_0 on 2017/8/7.
 */

public class LoginModel extends BaseModel {
    public LoginModel(Context context) {
        super(context);
    }

    public Observable<BaseInfo> getSmsCode(String mobile){
        return  Api.getInstance().getRetrofit()
                .create(MyApi.class)
                .getSmsCode(mobile)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<LoginInfo> goToLogin(String userMobile,String code){
        return Api.getInstance().getRetrofit()
                .create(MyApi.class)
                .getLogin(userMobile,code,"Android")
                .observeOn(AndroidSchedulers.mainThread());
    }






}

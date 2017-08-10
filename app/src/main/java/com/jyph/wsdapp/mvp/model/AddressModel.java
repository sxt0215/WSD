package com.jyph.wsdapp.mvp.model;

import android.content.Context;

import com.jyph.wsdapp.basemvp.model.base.BaseModel;
import com.jyph.wsdapp.common.bean.JsonRootBean;
import com.jyph.wsdapp.common.network.ApiGuge;
import com.jyph.wsdapp.mvp.wsdapi.MyApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by sxt_0 on 2017/8/4.
 */

public class AddressModel extends BaseModel {
    public AddressModel(Context context) {
        super(context);
    }

    public Observable<JsonRootBean> getAddress(String latitude){//Double longitude
        return ApiGuge.getInstance().getRetrofit()
                .create(MyApi.GetGugeAddress.class)
                .getAddreaa(latitude)//longitude
                .observeOn(AndroidSchedulers.mainThread());
    }
}

package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.common.bean.JsonRootBean;
import com.jyph.wsdapp.common.network.ApiException;
import com.jyph.wsdapp.common.rxjava.MyObserver;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.mvp.model.AddressModel;
import com.jyph.wsdapp.mvp.view.activity.MainActivity;

/**
 * Created by sxt_0 on 2017/8/4.
 */

public class AddressPresenter extends BasePresenter<MainActivity,AddressModel> {
    public AddressPresenter(Context context) {
        super(context);
    }

    @Override
    public AddressModel bindModel() {
        return new AddressModel(getContext());
    }

    //请求
    public void getAddress(String latitude){//Double longitude  longitude
        getModel().getAddress(latitude).subscribe(getAddress());
    }

    public MyObserver<JsonRootBean> getAddress(){
        MyObserver<JsonRootBean> myObserver = new MyObserver<JsonRootBean>(this) {
            @Override
            public void onNext(JsonRootBean value) {
                LogMe.d("位置",value.getResults().get(0).getFormatted_address());
                getView().setText(value.getResults().get(0).getFormatted_address());
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
}

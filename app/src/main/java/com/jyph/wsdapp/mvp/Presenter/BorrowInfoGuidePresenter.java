package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.common.bean.BorrowInfo;
import com.jyph.wsdapp.common.network.ApiException;
import com.jyph.wsdapp.common.rxjava.MyObserver;
import com.jyph.wsdapp.mvp.model.BorrowInfoGuideModel;
import com.jyph.wsdapp.mvp.view.activity.BorrowInfoGuideActivity;

/**
 * Created by sxt_0 on 2017/8/15.
 */

public class BorrowInfoGuidePresenter extends BasePresenter<BorrowInfoGuideActivity,BorrowInfoGuideModel> {
    public BorrowInfoGuidePresenter(Context context) {
        super(context);
    }

    @Override
    public BorrowInfoGuideModel bindModel() {
        return new BorrowInfoGuideModel(getContext());
    }

    public void getBorrowInfo(String userId){
        getModel().getBorrowInfo(userId).subscribe(getBorrowInfo());
    }

    public MyObserver<BorrowInfo> getBorrowInfo(){
        MyObserver<BorrowInfo> myObserver = new MyObserver<BorrowInfo>() {
            @Override
            public void onNext(BorrowInfo value) {

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

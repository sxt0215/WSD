package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.BorrowInfoEmergencyModel;
import com.jyph.wsdapp.mvp.view.activity.BorrowInfoEmergencyActivity;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowInfoEmergencyPresenter extends BasePresenter<BorrowInfoEmergencyActivity,BorrowInfoEmergencyModel> {
    public BorrowInfoEmergencyPresenter(Context context) {
        super(context);
    }

    @Override
    public BorrowInfoEmergencyModel bindModel() {
        return new BorrowInfoEmergencyModel(getContext());
    }
}

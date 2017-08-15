package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
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





}

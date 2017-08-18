package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.BorrowInfoBindCardModel;
import com.jyph.wsdapp.mvp.view.activity.BorrowInfoBindCardActivity;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowInfoBindCardPresenter extends BasePresenter<BorrowInfoBindCardActivity,BorrowInfoBindCardModel> {
    public BorrowInfoBindCardPresenter(Context context) {
        super(context);
    }

    @Override
    public BorrowInfoBindCardModel bindModel() {
        return new BorrowInfoBindCardModel(getContext());
    }
}

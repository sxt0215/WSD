package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.BorrowInfoCardModel;
import com.jyph.wsdapp.mvp.view.activity.BorrowInfoCardActivity;

/**
 * Created by sxt_0 on 2017/8/15.
 */

public class BorrowInfoCardPresenter extends BasePresenter<BorrowInfoCardActivity,BorrowInfoCardModel> {
    public BorrowInfoCardPresenter(Context context) {
        super(context);
    }

    @Override
    public BorrowInfoCardModel bindModel() {
        return new BorrowInfoCardModel(getContext());
    }
}

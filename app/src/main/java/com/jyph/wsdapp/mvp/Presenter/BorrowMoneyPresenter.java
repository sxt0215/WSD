package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.BorrowMoneyModel;
import com.jyph.wsdapp.mvp.view.activity.BorrowMoneyActivity;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowMoneyPresenter extends BasePresenter<BorrowMoneyActivity,BorrowMoneyModel> {
    public BorrowMoneyPresenter(Context context) {
        super(context);
    }

    @Override
    public BorrowMoneyModel bindModel() {
        return new BorrowMoneyModel(getContext());
    }
}

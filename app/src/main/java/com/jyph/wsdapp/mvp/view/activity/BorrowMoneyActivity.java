package com.jyph.wsdapp.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.mvp.Presenter.BorrowMoneyPresenter;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowMoneyActivity extends BaseActivity<BorrowMoneyPresenter> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_money);
    }

    @Override
    public BorrowMoneyPresenter bindPresenter() {
        return super.bindPresenter();
    }
}

package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.HomeModel;
import com.jyph.wsdapp.mvp.view.activity.HomeActivity;

/**
 * Created by sxt_0 on 2017/8/7.
 */

public class HomePresenter extends BasePresenter<HomeActivity,HomeModel> {
    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public HomeModel bindModel() {
        return null;
    }
}

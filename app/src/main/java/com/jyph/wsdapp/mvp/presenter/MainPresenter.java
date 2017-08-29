package com.jyph.wsdapp.mvp.presenter;

import android.content.Context;

import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.MainModel;
import com.jyph.wsdapp.mvp.view.activity.MainActivity;

/**
 * Created by sxt_0 on 2017/8/28.
 */

public class MainPresenter extends BasePresenter<MainActivity,MainModel> {
    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    public MainModel bindModel() {
        return new MainModel(getContext());
    }
}

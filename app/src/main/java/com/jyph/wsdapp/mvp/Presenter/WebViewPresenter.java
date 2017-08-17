package com.jyph.wsdapp.mvp.Presenter;

import android.content.Context;


import com.jyph.wsdapp.basemvp.presenter.base.BasePresenter;
import com.jyph.wsdapp.mvp.model.WebViewModel;
import com.jyph.wsdapp.mvp.view.activity.WebViewActivity;


/**
 * Created by sxt on 16/12/28.
 */
public class WebViewPresenter extends BasePresenter<WebViewActivity,WebViewModel> {
    public WebViewPresenter(Context context) {
        super(context);
    }

    @Override
    public WebViewModel bindModel() {
        return new WebViewModel(getContext());
    }







}
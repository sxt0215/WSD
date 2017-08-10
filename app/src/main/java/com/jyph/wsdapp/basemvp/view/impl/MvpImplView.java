package com.jyph.wsdapp.basemvp.view.impl;


import com.jyph.wsdapp.basemvp.view.MvpView;

/**
 * Created by sxt on 16/12/15.
 */
public interface MvpImplView<M> extends MvpView {
    void showLoading(boolean isShow);
    void showData(M data);
    void showError(Exception e);
}

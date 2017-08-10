package com.jyph.wsdapp.basemvp.view;


import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;

/**
 * Created by sxt on 16/12/15.
 */
public interface MvpView<P extends MvpPresenter> {
    P bindPresenter();
}

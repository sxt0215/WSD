package com.jyph.wsdapp.basemvp.view.base;


import com.jyph.wsdapp.basemvp.view.MvpView;

/**
 * Created by sxt on 16/12/15.
 */
public interface IBaseView<T> extends MvpView {
    void showDialog();
    void hideDialog();
    void loadData(T data, boolean isDownRefresh);
    void error(Exception e);
}

package com.jyph.wsdapp.basemvp.presenter;


import com.jyph.wsdapp.basemvp.model.MvpModel;
import com.jyph.wsdapp.basemvp.view.MvpView;

/**
 * Created by sxt on 16/12/15.
 */
public interface MvpPresenter<V extends MvpView,M extends MvpModel>  {
    void attach(V view);
    void destroy();
    //    V getView();
//    boolean isDestroy();
    M bindModel();
//    M getModel();
}

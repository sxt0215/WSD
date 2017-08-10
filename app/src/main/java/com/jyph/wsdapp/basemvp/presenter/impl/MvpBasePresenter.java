package com.jyph.wsdapp.basemvp.presenter.impl;


import com.jyph.wsdapp.basemvp.model.MvpModel;
import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;
import com.jyph.wsdapp.basemvp.view.MvpView;

/**
 * Created by sxt on 16/12/15.
 */
public abstract class MvpBasePresenter<V extends MvpView,M extends MvpModel> implements MvpPresenter<V,M> {
    private V view;
    private M model;

    public MvpBasePresenter() {
        this.model = bindModel();
    }

    public abstract M bindModel();

    protected M getModel() {
        if (model == null) {
            throw new NullPointerException("model没有绑定 不能使用");
        }
        return model;
    }

    @Override
    public void attach(V view) {
        this.view = view;
    }

    protected V getView() {
        return view;
    }

    protected boolean isDestroy() {
        return view == null;
    }

    @Override
    public void destroy() {
        view = null;
    }
}

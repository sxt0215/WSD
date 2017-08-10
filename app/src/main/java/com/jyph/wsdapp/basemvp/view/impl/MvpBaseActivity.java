package com.jyph.wsdapp.basemvp.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;
import com.jyph.wsdapp.basemvp.view.MvpView;


/**
 * Created by sxt on 16/12/15.
 */
public abstract class MvpBaseActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView<P> {
    private P presenter;
    public Activity mActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        presenter = bindPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }

    }

    protected P getPresenter() {
        if (presenter == null) {
            throw new NullPointerException("请先绑定presenter在使用");
        }
        return presenter;
    }

}

package com.jyph.wsdapp.basemvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;
import com.jyph.wsdapp.basemvp.view.MvpView;


/**
 * Created by sxt on 16/12/15.
 */
public abstract class MvpBaseFragment<P extends MvpPresenter> extends Fragment implements MvpView<P> {
    private P presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = bindPresenter();
        if(presenter != null){
            presenter.attach(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected P getPresenter() {
        if(presenter == null){
            throw new NullPointerException("请先绑定presenter在使用");
        }
        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null){
            presenter.destroy();
        }
    }
}

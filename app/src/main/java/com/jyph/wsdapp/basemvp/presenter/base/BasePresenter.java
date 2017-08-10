package com.jyph.wsdapp.basemvp.presenter.base;

import android.content.Context;


import com.jyph.wsdapp.basemvp.model.MvpModel;
import com.jyph.wsdapp.basemvp.presenter.impl.MvpBasePresenter;
import com.jyph.wsdapp.basemvp.view.MvpView;
import com.jyph.wsdapp.common.application.MyApplication;
import com.jyph.wsdapp.common.rxjava.RxLifeManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by sxt on 16/12/15.
 */
public abstract class BasePresenter<V extends MvpView,M extends MvpModel> extends MvpBasePresenter<V,M> implements RxLifeManager {
    private Context mContext;

    private CompositeDisposable disposables;

    public BasePresenter(Context context) {
        this.mContext = context.getApplicationContext();
        disposables = new CompositeDisposable();
    }

    public Context getContext() {
        return mContext;
    }
    public void addObserver(Disposable disposable){
        disposables.add(disposable);
    }

    @Override
    public void destroy() {
        super.destroy();
        disposables.dispose();
    }

    public boolean isLogin(){
        return MyApplication.getInstance().getSharePreference().isLogin();
    }
    public boolean isKaiHu(){
        return MyApplication.getInstance().getSharePreference().isKaiHu();
    }
}

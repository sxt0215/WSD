package com.jyph.wsdapp.common.rxjava;

import android.util.Log;


import com.jyph.wsdapp.common.network.ApiException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ==========================================
 * <p>
 * 作    者 : ying
 * <p>
 * 创建时间 ： 2016/9/13.
 * <p>
 * 用   途 :
 * <p>
 * <p>
 * ==========================================
 */
public abstract class MyObserver<T> implements Observer<T> {
    static String TAG = MyObserver.class.getSimpleName();
    private RxLifeManager rxLifeManager;

    public MyObserver(RxLifeManager rxLifeManager) {
        this.rxLifeManager = rxLifeManager;
    }

    public MyObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.e(TAG,"Disposable");
        if (rxLifeManager != null) {
            rxLifeManager.addObserver(d);
        }
    }


    @Override
    public void onComplete() {
        Log.e(TAG,"onComplete");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {
            onApiError((ApiException) e);
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            onNetworkException(e);
        } else {
            onUnknownException(e);
        }
    }


    public void onApiError(ApiException exception) {
        Log.e(TAG,"onApiError");
    }

    public void onNetworkException(Throwable e) {
        Log.e(TAG,"onNetworkException");
    }

    public void onUnknownException(Throwable e) {
        Log.e(TAG,"onUnknownException");
    }
}

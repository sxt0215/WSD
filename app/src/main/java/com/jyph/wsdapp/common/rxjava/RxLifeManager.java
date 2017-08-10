package com.jyph.wsdapp.common.rxjava;

import io.reactivex.disposables.Disposable;

/**
 * Created by sxt on 16/12/15.
 */
public interface RxLifeManager {
    void addObserver(Disposable disposable);
}

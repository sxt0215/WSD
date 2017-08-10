package com.jyph.wsdapp.basemvp.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jyph.wsdapp.basemvp.presenter.MvpPresenter;
import com.jyph.wsdapp.basemvp.view.impl.MvpBaseFragment;


/**
 * Created by sxt on 16/12/15.
 */
public abstract class BaseFragment<P extends MvpPresenter> extends MvpBaseFragment<P> {
    private View view;
    public  String TAG = getClass().getSimpleName();
    public Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mActivity = getActivity();
        if (view == null) {
            view = getRootView(inflater,container);
            initView(view);
            Log.e(TAG, "onCreateView __ initView");
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            Log.e(TAG, "onCreateView __ removeView");
        }
        Log.e(TAG, "onCreateView end");
        return view;
    }

    protected abstract void initView(View view);

    public abstract View getRootView(LayoutInflater inflater, @Nullable ViewGroup container) ;

    public void showSnackbar(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(CharSequence text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }



}

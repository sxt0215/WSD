package com.jyph.wsdapp.mvp.wsdapi;

import java.util.List;

/**
 * Created by xiaoling on 2016-10-30.
 * 用途：网络请求之后的成功、失败、没网等操作
 * T:实体类泛型
 */
public interface DoAfterApi<T> {

    public static final int LOADING_DIALOG = 0;//进入页面时的加载
    public static final int LOADING_DOWN = 1;//下拉刷新
    public static final int LOADING_UP = 2;//上拉加载
    public static final int LOADING_FINISH = 3;//上拉加载

    /**
     * 网络请求成功
     * @param subList 返回的列表
     */
    void onSuccess(List<T> subList, String message);

    /**
     * 请求失败，显示异常页面。
     * 1.startpage ==0,网络异常，显示异常页面，自定义code = -1，
     * 2.startPage == 0，内容为空，显示异常页面，code一般 == 200
     * 3.startpage ==0,服务器访问异常，显示异常页面，code为服务器返回的code
     * @param code
     * @param message
     */
    void onFailShowErroPage(int code, String message);

    /**
     * 只要startPage ！= 0，任何加载异常的情况都只弹土司
     * @param message
     */
    void onEmpty(String message);

    /**
     * 加载状态
     * @param isDownRefresh 0：进入页面时的加载
     *                      1：下拉刷新
     *                      2：上拉加载
     */
    void onLoading(int isDownRefresh);
}



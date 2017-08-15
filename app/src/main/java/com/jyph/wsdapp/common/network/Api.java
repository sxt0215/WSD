package com.jyph.wsdapp.common.network;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jyph.wsdapp.common.application.MyApplication;
import com.jyph.wsdapp.common.application.MyApplicationLike;
import com.jyph.wsdapp.common.utils.MyUtils;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * ==========================================
 * <p>
 * 作    者 : sxt_0
 * <p>
 * 创建时间 ： 2017/8/1..
 * <p>
 * 用   途 :
 * <p>
 * <p>
 * ==========================================
 */
public class Api {
    public final static int PAYMENT_VENDOR_UMP = 1;

    private static Retrofit retrofit;

    public static OkHttpClient client;

    public static Api getInstance() {
        return SingletonInstance.instance;
    }

    private static class SingletonInstance {
        public final static Api instance = new Api();
    }

//    public  String

    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor) //添加log拦截器
                .addInterceptor(chain -> { //添加请求头信息
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder();
                    //这里添加请求头信息
//                    Request build = builder1.addHeader("apikey", "3c00d3dcb492f7098699aae47b08c468").build();
//                    Request build = builder1.addHeader("apikey", "ac7c302dc489a69082cbee6a89e3646c").build();
                    builder1.addHeader("Req_devId", MyUtils.getUUid(MyApplicationLike.getInstance().getApplication()));
                    if(!TextUtils.isEmpty(MyUtils.getToken())){
                        builder1.addHeader("Req_devicetoken", MyUtils.getToken());
                    }
                    Request build = builder1.build();
                    Response response = chain.proceed(build);
                    //这里获取响应头信息
                    String req_devicetoken = response.header("Req_devicetoken");
                    if(!TextUtils.isEmpty(req_devicetoken)){
                        MyUtils.setToken(req_devicetoken);
                    }
                    return response;
                })

                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiSettings.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))  //指定所有的网络请求都在IO线程中做
                .addConverterFactory(MyRetrofitConverterFactory.create())
                .client(client)
                .build();
    }

    public static void setRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor) //添加log拦截器
                .addInterceptor(chain -> { //添加请求头信息
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder();
                    //这里添加请求头信息
//                    Request build = builder1.addHeader("apikey", "3c00d3dcb492f7098699aae47b08c468").build();
//                    Request build = builder1.addHeader("apikey", "ac7c302dc489a69082cbee6a89e3646c").build();
                    builder1.addHeader("Req_devId", MyUtils.getUUid(MyApplicationLike.getInstance().getApplication()));
                    if(!TextUtils.isEmpty(MyUtils.getToken())){
                        builder1.addHeader("Req_devicetoken", MyUtils.getToken());
                    }
                    Request build = builder1.build();
                    Response response = chain.proceed(build);
                    //这里获取响应头信息
                    String req_devicetoken = response.header("Req_devicetoken");
                    if(!TextUtils.isEmpty(req_devicetoken)){
                        MyUtils.setToken(req_devicetoken);
                    }
                    return response;
                })

                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiSettings.URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))  //指定所有的网络请求都在IO线程中做
                .addConverterFactory(MyRetrofitConverterFactory.create())
                .client(client)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}

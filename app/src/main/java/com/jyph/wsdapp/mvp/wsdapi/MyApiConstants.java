package com.jyph.wsdapp.mvp.wsdapi;


/**
 * Created by sxt on 16/12/19.
 */
public class MyApiConstants {
    //测试环境  https://lmjf.uats.cc/   rapapi.org/mockjs/23852
    public final static String IP_UAT = "http://rapapi.org/mockjs/";
    public final static String IP_UAT_V2 = "https://lmjf.uats.cc/api/v2/";
    public final static String CLIENT_ID_UAT = "client-id-for-mobile-dev";
    public final static String CLIENT_SECRET_UAT = "client-secret-for-mobile-dev";
    //H5 测试地址
    public final static String IP_H5_UAT = "https://lmjf.uats.cc/";
    //生产环境
    public final static String IP_RELEASE = "http://106.14.237.21/";
    public final static String IP_RELEASE_V2 = "http://106.14.237.21/api/v2/";
    public final static String CLIENT_ID_RELEASE = "8e43152a-211a-4bbe-8eee-32bb073aef20";
    public final static String CLIENT_SECRET_RELEASE = "e5f10d1243fe3cb575f0d3480cf2d627d37d9ca0c5d869effc1d2b77708ec67d";


    /**
     * 登录短信验证码获取
     * */
    public final static String API_LOGIN_SMS_CODE="register/smsCaptcha";
    /**
     * 登录接口
     * */
    public final static String API_LOGIN = "23852/wsd/login";








    /**
     * 首页轮播图
     * */
    public final static String API_BANNERS = "cms/mobileBanners";

    /**
     * 首页CMS 最新G公告
     * */
    public final static String API_PUBLICATION = "cms/category/PUBLICATION/name/{publication}";

    /**
     * 首页标的   ?groupByProductKey=true   因返回的不是最新 新手标
     *            loans/home/summary       说影响pc首页
     * loans/app/home
     * */
    public final static String API_HOMELOAN = "loans/app/home";

    //检查升级链接
    public final static String VERSION_UPDATE_LINK = "http://app.creditcloud.com/lmjr/android/app.json";


    //========================================================登录
    /**
     * 检测手机号是否注册
     * */
    public final static String API_CHECKPHONE = "users/check/mobile";

}

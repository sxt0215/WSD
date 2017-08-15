package com.jyph.wsdapp.common.network;


import com.jyph.wsdapp.mvp.wsdapi.MyApiConstants;

/**
 * Created by sxt on 16/8/4.
 */
public class ApiSettings {
    public static String BASEURL = MyApiConstants.IP_UAT;
    public static String URL_BASE = MyApiConstants.IP_UAT_V2;
    public static String CLIENT_ID = MyApiConstants.CLIENT_ID_UAT;
    public static String CLIENT_SECRET = MyApiConstants.CLIENT_SECRET_UAT;

    public static String BASEURL_H5 = MyApiConstants.IP_H5_UAT;

    //版本升级地址  gitlab 地址
//    public static String APP_VERSION_URL = "http://gitlab.creditcloud.com/";
    //运维（健哥） 地址
    public static String APP_VERSION_URL = "http://app.creditcloud.com/";

    public static String GRANT_TYPE = "password";
    //只登录需要
    public static String CLIENT_CODE = "手机用户";

    public static String URL_THIRD_PARTY_PAYMENT = "http://pay.soopay.net/spay/pay/payservice.do";

    public static String API_VERSION = "http://101.226.171.148/app.json";

    public static int PAYMENT_VENDOR = Api.PAYMENT_VENDOR_UMP;
}

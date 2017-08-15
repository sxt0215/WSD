package com.jyph.wsdapp.basemvp.model.base;

import android.content.Context;
import android.util.Log;


import com.jyph.wsdapp.basemvp.model.impl.MvpBaseModel;
import com.jyph.wsdapp.common.application.MyApplication;
import com.jyph.wsdapp.common.application.MyApplicationLike;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * Created by sxt on 16/12/15.
 */
public abstract class BaseModel extends MvpBaseModel {
    //请求失败
    public static final int REQUEST_ERR = -1;
    //没有网络连接
    public static final int NOT_NET_ERR = -2;


    private String serverUrl = "http://api.budejie.com";

    private Context mContext;

    public BaseModel(Context context) {
        this.mContext = context;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public Context getContext() {
        return mContext;
    }

    public String getToken(){
        return "Bearer "+ MyApplicationLike.getInstance().getSharePreference().getToken();
    }
    public String getUserId(){
        return MyApplicationLike.getInstance().getSharePreference().getUserId();
    }

    /**
     * 获取ip地址
     * @return
     */
    public  String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("ip", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }
}

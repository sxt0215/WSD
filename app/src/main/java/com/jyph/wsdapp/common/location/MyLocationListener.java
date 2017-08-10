//package com.jieyuepuhui.wsdapp.common.location;
//
//import android.location.Location;
//import android.location.LocationProvider;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.jieyuepuhui.wsdapp.common.utils.LogMe;
//
//import static android.content.ContentValues.TAG;
//
///**
// * Created by sxt_0 on 2017/8/2.
// */
//
//public class MyLocationListener implements android.location.LocationListener {
//
//    //当设备位置发生变化时调用该方法  
//    @Override
//    public void onLocationChanged(Location location) {
////        updateView(location);
//        LogMe.i(TAG, "时间："+location.getTime());
//        LogMe.i(TAG, "经度："+location.getLongitude());
//        LogMe.i(TAG, "纬度："+location.getLatitude());
//        LogMe.i(TAG, "海拔："+location.getAltitude());
//    }
//
//    /**
//     * 当provider的状态发生变化时调用该方法.比如GPS从可用变为不可用. 
//     * GPS状态变化时触发 
//     */
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        switch (status){
//            case LocationProvider.AVAILABLE:
//                LogMe.i(TAG,"GPS状态  可见状态");
//                break;
//            case LocationProvider.OUT_OF_SERVICE:
//                LogMe.i(TAG,"GPS状态  服务区外状态");
//                break;
//            case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                LogMe.i(TAG,"GPS状态  暂停服务状态");
//                break;
//        }
//    }
//
//    //当provider被打开的瞬间调用该方法.比如用户打开GPS  
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    //当provider被关闭的瞬间调用该方法.比如关闭打开GPS   
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//}

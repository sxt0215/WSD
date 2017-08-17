package com.jyph.wsdapp.common.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by sxt on 16/8/17.
 */
public class BaseInfo {
    private boolean success;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;


    /**
     * 错误列表
     */
//    private List<ApiError> error;

    private boolean  needToLogin = false;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



//    public List<ApiError> getError() {
//        return error;
//    }
//
//    public void setError(List<ApiError> error) {
//        this.error = error;
//    }

    public boolean isNeedToLogin() {
        return needToLogin;
    }

    public void setNeedToLogin(boolean needToLogin) {
        this.needToLogin = needToLogin;
    }

//    public boolean isError(){
//        if(error != null && error.size()>0){
//            return !TextUtils.isEmpty(error.get(0).getMessage());
//        }else{
//            return false;
//        }
//    }

//    public String getErrorMessage(){
//        try{
//            return ErrorInfo.getMsg(error.get(0).getMessage());
//        }catch(Exception e){
//            return "未知错误";
//        }
//    }

}

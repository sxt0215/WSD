package com.jyph.wsdapp.common.network;

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
public class ApiException extends RuntimeException {
    private int code;
    private String msg;
    private String response;

    public ApiException(int code, String msg, String response) {
        super(code+"---"+msg+"--"+response);
        this.code = code;
        this.msg = msg;
        this.response = response;

    }

    public ApiException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String respones) {
        this.response = respones;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", response='" + response + '\'' +
                "} " + super.toString();
    }
}

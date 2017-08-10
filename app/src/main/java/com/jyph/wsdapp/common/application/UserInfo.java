package com.jyph.wsdapp.common.application;

/**
 * Created by creditcloud on 7/26/16.
 */
public class UserInfo {

    private String userId;
    private String token;
    private String userName;
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    private String userMobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private int accountStatus;

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public UserInfo(String userName, String userId, String userMobile, String token, int accountStatus, String userEmail) {
        this.userName = userName;
        this.userId = userId;
        this.userMobile = userMobile;
        this.token = token;
        this.accountStatus = accountStatus;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void clear(){
        this.userId = "";
        this.token = "";
        this.userName = "";
        this.userEmail = "";
    }

}

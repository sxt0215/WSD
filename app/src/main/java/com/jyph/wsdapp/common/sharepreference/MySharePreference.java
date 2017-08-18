package com.jyph.wsdapp.common.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.jyph.wsdapp.common.text.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 用于全局变量检测是否第一次启动和登录信息
 */
public class MySharePreference {

    private static MySharePreference mySharePreference;
    private Context context;


    public MySharePreference(Context context) {
        this.context = context;
    }

    public static MySharePreference newInstance(Context context) {
        if (mySharePreference == null) {
            mySharePreference = new MySharePreference(context);
        }
        return mySharePreference;
    }

    /**
     *第一次启动
     *
     * @return
     */
    public boolean isFirstLaunch() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("isfirstlaunch", true);
    }

    public void setFirstLaunch() {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("isfirstlaunch", false);
        editor.commit();
    }

    public static SharedPreferences getDefaultSP(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Editor getEditor(Context context){
        return getDefaultSP(context).edit();
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        return editor.commit();
    }
    /**不存在返回false*/
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getDefaultSP(context).getBoolean(key, defaultValue);
    }

    public static void setFirstIn(Context context) {
        putBoolean(context,"isFirstIn",true);
    }

    public static boolean isFirstIn(Context context) {
        return getBoolean(context,"isFirstIn");
    }

    /**
     * gesture switch
     */
    public boolean isGestureOpen() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("GestureSwitch", true);
    }

    public void setGestureSwitch(boolean status) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("GestureSwitch", status);
        //Log.e("GestureSwitch","status:"+status);
        editor.commit();
    }

    public void setGestureTime(long time) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putLong("gestureTime", time);
        editor.commit();
    }

    public long getGestureTime() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getLong("gestureTime", System.currentTimeMillis());
    }

    public void setGesture(String gesture, String userid) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putString("gesture"+userid, StringUtils.getMD5(gesture));
        editor.commit();
    }


    public String getGesture(String userid) {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("gesture" + userid , "");
    }
    //======================================================================记录MainActivity当前的Fragment
    public void setCurrentFragment(int fragmentIndex) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putInt("fragmentIndex", fragmentIndex);
        editor.apply();
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getUserId());
    }


    public void setLogin(boolean islogin, String username, String userid,
                         String mobile, String token, int accountStatus, String userEmail) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("isLogin", islogin);
        editor.putString("userid", userid);
        editor.putString("username", username);
        editor.putString("mobile", mobile);
        editor.putString("token", token);
        editor.putInt("accountStatus",accountStatus);
        editor.putString("userEmail",userEmail);
        editor.commit();
    }

    public void setUserName(String username, String userid) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putString("username"+userid, username);
        editor.commit();
    }

    public String getUserName(String userid) {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("username" +userid, "");
    }


    public String getMobile() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("mobile", "");
    }

    public String getUserEmail(){
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("userEmail","");
    }

    public String getUsername(String userid) {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("username" + userid, "");
    }

    public String getUserId() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("userid", "");
    }

    /**
     * 用户资料完善情况设置==================================================================
     * */
    public void setEmergency(boolean isEmergency){
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("emergency", isEmergency);
        editor.commit();
    }

    public boolean isIdentity() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("identity", false);
    }

    public void setIdentity(boolean identity){
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("identity", identity);
        editor.commit();
    }

    public boolean isOtherInfo() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("otherInfo", false);
    }

    public void setOtherInfo(boolean otherInfo){
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("otherInfo", otherInfo);
        editor.commit();
    }

    public boolean isUndetermined() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("undetermined", false);
    }

    public void setUndetermined(boolean undetermined){
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("undetermined", undetermined);
        editor.commit();
    }

    public boolean isEmergency() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("emergency", false);
    }

    //=============================================================================================


    public void setKaihu(boolean isKaihu) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("kaihu", isKaihu);
        editor.commit();
    }

    public boolean isKaiHu() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getBoolean("kaihu", false);
    }

    /*
     * @param
     */
    public void setToken(String access_token) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putString("token", access_token);
        editor.commit();
    }

    public String getToken() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getString("token", "");
    }

    public void setAccountStatus(int accountStatus) {
        SecurePreferences preferences = new SecurePreferences(context);
        Editor editor = preferences.edit();
        editor.putInt("accountStatus", accountStatus);
        editor.commit();
    }

    public int getAccountStatus() {
        SecurePreferences preferences = new SecurePreferences(context);
        return preferences.getInt("accountStatus",0);
    }

    /**
     *写文件
     *
     * @param filename
     * @param data
     * @param <T>
     */
    public <T> void writeToFile(String filename, T... data) {
        // TODO Auto-generated method stub
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(context.getFilesDir().toString() + "/"
                            + filename));
            for (T list : data) {
                out.writeObject(list);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     *
     * @param filename
     * @param <T>
     * @return
     */
    public <T> Object getFromFile(String filename) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(context.getFilesDir().toString() + "/"
                            + filename));
            Object data = inputStream.readObject();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

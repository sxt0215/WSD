package com.jyph.wsdapp.common.network;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

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
public class MyResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    public static final int OK = 200;
    private static final String code = "code";
    private static final String msg = "message";
    private static final String response = "response";

    private Gson gson;
    private Type type;

    public MyResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        value.close();
        Log.d("-------Conver--",type.getClass().getCanonicalName());
        return gson.fromJson(string,type);
//        try {
//            JSONObject jsonObject = new JSONObject(string);
//            int rcode = jsonObject.optInt(code);
//            if(rcode != OK) {
//                throw new ApiException(rcode,jsonObject.optString(msg),"");
//            }
//            return gson.fromJson(string,type);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            throw new ApiException(-1,"json解析失败返回数据格式有问题",string);
//        }finally {
//            value.close();
//        }
    }
}

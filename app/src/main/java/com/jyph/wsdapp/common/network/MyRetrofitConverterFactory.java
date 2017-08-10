package com.jyph.wsdapp.common.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

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
public class MyRetrofitConverterFactory extends Converter.Factory {
    public static MyRetrofitConverterFactory create() {
        return create(new Gson());
    }

    public static MyRetrofitConverterFactory create(Gson gson) {
        return new MyRetrofitConverterFactory(gson);
    }

    private final Gson gson;

    private MyRetrofitConverterFactory(Gson gson) {
        if(gson == null){
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new MyResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyRequestBodyConverter<>(gson, adapter);
    }
}

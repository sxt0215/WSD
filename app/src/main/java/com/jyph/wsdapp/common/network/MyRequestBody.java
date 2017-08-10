package com.jyph.wsdapp.common.network;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyRequestBody {

    /**
     * 适合上传文件，文字类型
     * @param keyName 服务器要的字段名
     * @param filePath 文件路径
     * @return
     */
    public static RequestBody getFormDataFilePart(String keyName, String filePath) {
        File file = new File(filePath);
        //指定文件类型为复合/格式数据，上传文本时会自动转义字符
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);

    }

    /**
     * 上传图片或多媒体文件
     * @param filePath 文件路径
     * @return
     */
    public static RequestBody getImgFilePart(String filePath) {
        File file = new File(filePath);
        //指定文件类型为二进制流
        return RequestBody.create(MediaType.parse("application/octet-stream"), file);

    }
}

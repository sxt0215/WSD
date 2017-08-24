package com.jyph.wsdapp.common.utils.file;


import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jyph.wsdapp.common.utils.LogMe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sxt_0 on 2017/8/23.
 */

public class CreateFiles {

    //首先判断是否存在SD卡  当没有外挂SD卡时，内置ROM也被识别为存在sd卡
    public static boolean isSDCardExist(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    //获取SD卡根目录路径
    public static String getSDCardPath(){
        boolean exist = isSDCardExist();
        String sdpath = "";
        if(exist){
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }else{
            sdpath = "不适用";
        }
        return sdpath;
    }

    //获取默认的文件存储路径
    public static String getDefaultFilePath(){
        String filepath = "";
        File file = new File(Environment.getExternalStorageDirectory(),"abc.txt");
        if(file.exists()){
            filepath = file.getAbsolutePath();
        }else{
            filepath = "不适用";
        }
        return filepath;
    }

    /**
     * 读写/data/data/<应用程序名>目录下的文件:==============================
     * */
    //写数据
    public static void writeFile(String fileName, String writestr, Context context){
        try{
            FileOutputStream fout =context.openFileOutput(fileName, MODE_PRIVATE);
            byte [] bytes = writestr.getBytes();
            fout.write(bytes);
            LogMe.d("写入","读写成功");
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //读数据
    public String readFile(String fileName,Context context) throws IOException{
        String res="";
        try{
            FileInputStream fin = context.openFileInput(fileName);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    //============================读写/data/data/<应用程序名>目录下的文件:===============================

    /**
     *读写SD卡中的文件。也就是/mnt/sdcard/目录下面的文件 ：
     * */
    public static void savaFileToSD(String filename, String filecontent,Context context) throws IOException {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            LogMe.d("filename ",filename);
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的  FileOutputStream
            BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));
            output.write(filecontent);
            //将String字符串以字节流的形式写入到输出流中
            output.flush();
            //关闭输出流
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public static String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        ArrayList<String> list = new ArrayList<>();
        Gson gson = new Gson();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
                list.add(line);
            }
            bufferedReader.close();
            LogMe.d("数组",""+list);
            LogMe.d("JSON格式", gson.toJson(list));
        }
        return sb.toString();
    }

    //=========================读写SD卡中的文件。也就是/mnt/sdcard/目录下面的文件;===========================


}

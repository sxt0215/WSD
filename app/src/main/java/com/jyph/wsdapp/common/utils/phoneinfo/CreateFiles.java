package com.jyph.wsdapp.common.utils.phoneinfo;


import android.content.Context;

import com.jyph.wsdapp.common.utils.LogMe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sxt_0 on 2017/8/23.
 */

public class CreateFiles {

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
    //写数据到SD中的文件
    public static void writeFileSdcardFile(String fileName,String write_str){
        try{
            FileOutputStream fout = new FileOutputStream(fileName);
            byte [] bytes = write_str.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //读SD中的文件
    public String readFileSdcardFile(String fileName) throws IOException{
        String res="";
        try{
            FileInputStream fin = new FileInputStream(fileName);
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

    //=========================读写SD卡中的文件。也就是/mnt/sdcard/目录下面的文件;===========================


}

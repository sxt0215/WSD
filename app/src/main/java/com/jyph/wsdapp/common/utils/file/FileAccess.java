package com.jyph.wsdapp.common.utils.file;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * Created by sxt_0 on 2017/8/24.
 */

public class FileAccess implements Serializable {
    RandomAccessFile oSavedFile;
    long nPos;

    public FileAccess() throws IOException{
        this("",0);
    }
    public FileAccess(String sName,long nPos)throws IOException{
        oSavedFile = new RandomAccessFile(sName,"rw");//创建一个随机访问文件类，可读写模式  
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }
    public synchronized int write(byte[] bytes,int nStart,int nLen){
        int n = -1;
        try{
            oSavedFile.write(bytes,nStart,nLen);
            n = nLen;
        }catch (IOException e){
            e.printStackTrace();
        }
        return n;
    }

    //每次读取1024*100字节
    public synchronized Detail getContent(long nStart){
        Detail detail = new Detail();
        detail.bytes = new byte[102400];
        try{
            oSavedFile.seek(nStart);
            detail.length = oSavedFile.read(detail.bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return detail;
    }
    public class Detail{
        public byte[] bytes;
        public int length;
    }
    //获取文件长度
    public long getFileLength(){
        long length = 0l;
        try {
            length = oSavedFile.length();
        }catch (IOException e){
            e.printStackTrace();
        }
        return length;
    }


}







































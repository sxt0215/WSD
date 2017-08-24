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

    public FileAccess throws IOException{
        this("",0);
    }
    public FileAccess(String sName,long nPos)throws IOException{
        oSavedFile = new RandomAccessFile(sName,"rw");//创建一个随机访问文件类，可读写模式  
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }
    public synchronized int write(byte[] bytes,int nStart,int nLen){
        int n = -1;

    }


}







































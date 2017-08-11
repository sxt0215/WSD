package com.jyph.wsdapp.common.utils.phoneinfo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.jyph.wsdapp.common.utils.LogMe;

import java.util.Date;


/**
 * Created by sxt_0 on 2017/8/4.
 * 通讯录信息
 */

public class ContactInfo {
    private static final String TODO = "";

    /**
     * 获取所有短信信息
     * */
    @TargetApi(Build.VERSION_CODES.N)
    public static String getSmdInPhone(Context context) {
        final String SMS_URI_ALL = "content://sms/";
        StringBuilder smsBuilder = new StringBuilder();
        try{
            Uri uri = Uri.parse(SMS_URI_ALL);
            String[] projection = new String[]{"_id", "address", "persion", "body", "date", "type"};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, "date desc");//获取手机内部短信

            if (cursor.moveToFirst()) {
                int index_Address = cursor.getColumnIndex("address");
                int index_Persion = cursor.getColumnIndex("persion");
                int index_Body = cursor.getColumnIndex("body");
                int index_Date = cursor.getColumnIndex("date");
                int index_Type = cursor.getColumnIndex("type");
                do {
                    String strAddress = cursor.getString(index_Address);
                    int intPersion = cursor.getInt(index_Persion);
                    String strBody = cursor.getString(index_Body);
                    long longDate = cursor.getLong(index_Date);
                    int intType = cursor.getInt(index_Type);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = new Date(longDate);
                    String strDate = dateFormat.format(date);

                    String strType = "";
                    if (intType == 1) {
                        strType = "接收";
                    } else if (intType == 2) {
                        strType = "发送";
                    } else {
                        strType = "null";
                    }

                    smsBuilder.append("[");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPersion + ", ");
                    smsBuilder.append(strBody + ", ");
                    smsBuilder.append(strDate + ", ");
                    smsBuilder.append(strType);
                    smsBuilder.append("]\n\n");

                } while (cursor.moveToNext());

                if (!cursor.isClosed()) {
                    cursor.close();
                    cursor = null;
                }
            } else {
                smsBuilder.append("no result");
            }//end if
            smsBuilder.append("getSmsInPhone has executed");
        }catch (SQLiteException ex){
            LogMe.i("SQLiteException in getSmsInPhone", ex.getMessage());
            Toast.makeText(context,"SQLiteException",Toast.LENGTH_SHORT);
        }
        return smsBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getSmsInPhone(Context context) {
        final String SMS_URI_ALL = "content://sms/"; // 所有短信
        final String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
        final String SMS_URI_SEND = "content://sms/sent"; // 已发送
        final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
        final String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
        final String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
        final String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表

        StringBuilder smsBuilder = new StringBuilder();

        try {
            Uri uri = Uri.parse(SMS_URI_ALL);
            String[] projection = new String[] { "_id", "address", "person",
                    "body", "date", "type", };
            Cursor cur = context.getContentResolver().query(uri, projection, null,
                    null, "date desc"); // 获取手机内部短信
            // 获取短信中最新的未读短信
            // Cursor cur = getContentResolver().query(uri, projection,
            // "read = ?", new String[]{"0"}, "date desc");
            if (cur.moveToFirst()) {
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");

                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    String strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int intType = cur.getInt(index_Type);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(longDate);
                    String strDate = dateFormat.format(d);

                    String strType = "";
                    if (intType == 1) {
                        strType = "接收";
                    } else if (intType == 2) {
                        strType = "发送";
                    } else if (intType == 3) {
                        strType = "草稿";
                    } else if (intType == 4) {
                        strType = "发件箱";
                    } else if (intType == 5) {
                        strType = "发送失败";
                    } else if (intType == 6) {
                        strType = "待发送列表";
                    } else if (intType == 0) {
                        strType = "所以短信";
                    } else {
                        strType = "null";
                    }

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(strDate + ", ");
                    smsBuilder.append(strType);
                    smsBuilder.append(" ]\n\n");
                } while (cur.moveToNext());

                if (!cur.isClosed()) {
                    cur.close();
                    cur = null;
                }
            } else {
                smsBuilder.append("no result!");
            }

            smsBuilder.append("getSmsInPhone has executed!");

        } catch (SQLiteException ex) {
            LogMe.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }

        return smsBuilder.toString();
    }




    /**
     * 获取通话记录
     * */
    @TargetApi(Build.VERSION_CODES.N)
    public static String getCallInPhone(Context context) {
        String result = null;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        Cursor cursor = context.getContentResolver().query(CallLog.
                        Calls.CONTENT_URI, new String[]{CallLog.Calls.DURATION, CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.NUMBER},
                null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        boolean hasRecord = cursor.moveToFirst();
        int count = 0;
        String strPhone = "";
        String date;

        while (hasRecord){
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            long duration = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION));
            strPhone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))));
            date = dateFormat.format(d);

            result = result + "phone :" + strPhone + ",";
            result = result + "date :" + date + ",";
            result = result + "time :" + duration + ",";

            switch (type){
                case CallLog.Calls.INCOMING_TYPE:
                    result = result + "type:呼入";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    result = result+ "type:呼出";
                    break;
                default:
                    break;
            }
            result += "\n";
            count ++ ;
            hasRecord = cursor.moveToNext();
        }
        LogMe.i("通话记录结果",result);
        return result;
    }

    /**
     * 手机联系人
     * */
    public static String getContact(Context context){
        String string = "";
        int count = 0;
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            //获取联系人的姓名索引
            int nameIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String contact = cursor.getString(nameIndex);
            string += contact + ":";
            //获取联系人的ID索引
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //查询该联系人的电话号码，类似的可以查询email phono

            Cursor phone = resolver.query(//第一个参数是确定查询电话号，第三个是查询具体某个人的过滤值
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null);
            //一个人可能有几个号码
            while (phone.moveToNext()){
                String strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                string = string + strPhoneNumber+";";
            }

            string += "\n";
            count ++ ;
            phone.close();
        }
        cursor.close();
        LogMe.i("联系人信息",String.valueOf(count));
        return string;
    }

    /**
     * 获取指定联系人电话号码
     * */
    public static String linkman(int requestCode,Intent data,Context context){
        String number = "";
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return "";
                }
                Uri uri = data.getData();
                Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                LogMe.i("获取手机号", "number" + number);
//                ed_phonebook.setText(number);
                break;
            default:
                break;
        }
        return number;
    }


}











































package com.jyph.wsdapp.mvp.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.application.MyApplicationLike;
import com.jyph.wsdapp.common.location.LocationUtils;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.common.utils.phoneinfo.ContactInfo;
import com.jyph.wsdapp.common.utils.file.CreateFiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {

    private TextView tv_location,tv_phonebook,ed_phonebook,tv_yasuo,tv_yuantu,tv_address,
            tv_phonebook_all,ed_phonebook_all,
            tv_phonebook_call,ed_phonebook_call,
            tv_phonebook_sms,ed_phonebook_sms;
    private Button btn_camera,btn_tiao;
    private ImageView yuan_image,ya_image,left_img;
    private EditText ed_location;
    private boolean flag,flagCamera;
    private static Context context;

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_CONTACTS_PERM = 122;//
    private static final int RC_SETTINGS_SCREEN = 125;
    private static final int ACTION_TAKE_PHOTO = 1;
    private File currentImageFile, file,filepath;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MyApplicationLike.getInstance().getApplication();

        LogMe.d("RegistrationID ==》",JPushInterface.getRegistrationID(context));
        initView();
        requestPermissions(); // 位置 相机 通讯录

        //解决安卓  7.0  android.os.FileUriExposedException:问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    private void initView() {
        tv_location = (TextView) findViewById(R.id.tv_location);
        ed_location = (EditText) findViewById(R.id.ed_location);
        tv_address = (TextView) findViewById(R.id.tv_address);
        ed_phonebook = (TextView) findViewById(R.id.ed_phonebook);
        tv_phonebook = (TextView) findViewById(R.id.tv_phonebook);
        tv_phonebook_all = (TextView) findViewById(R.id.tv_phonebook_all);
        ed_phonebook_all = (TextView) findViewById(R.id.ed_phonebook_all);
        tv_phonebook_call = (TextView) findViewById(R.id.tv_phonebook_call);
        ed_phonebook_call = (TextView) findViewById(R.id.ed_phonebook_call);
        tv_phonebook_sms = (TextView) findViewById(R.id.tv_phonebook_sms);
        ed_phonebook_sms = (TextView) findViewById(R.id.ed_phonebook_sms);
        tv_yuantu = (TextView) findViewById(R.id.tv_yuantu);
        tv_yasuo = (TextView) findViewById(R.id.tv_yasuo);
        btn_camera = (Button) findViewById(R.id.camera);
        yuan_image = (ImageView) findViewById(R.id.yuan_image);
        ya_image = (ImageView) findViewById(R.id.yu_image);
        left_img = (ImageView) findViewById(R.id.left_img);
        btn_tiao = (Button) findViewById(R.id.btn_tiao);
        tv_location.setOnClickListener(this);
        tv_phonebook.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        tv_phonebook_all.setOnClickListener(this);
        tv_phonebook_call.setOnClickListener(this);
        tv_phonebook_sms.setOnClickListener(this);
        left_img.setOnClickListener(this);
        btn_tiao.setOnClickListener(this);
    }

    public void setText(String text){
        tv_address.setText(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initPermission();//针对6.0以上版本做权限适配
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_location:
                getBestLocation();
                break;
            case R.id.tv_phonebook:
                jumpOpen();
                break;
            case R.id.camera:
                getCamera();
                break;
            case R.id.tv_phonebook_all:
                String info = ContactInfo.getContact(context);
                ed_phonebook_all.setText(info);
//                String filePath = "/sdcard/Test";
                String fileName = "ffff.txt";
                //读写/data/data/<应用程序名>目录下的
//                CreateFiles.writeFile(fileName,info,context);
                //写数据到SD中的文件
                CreateFiles.writeFile(fileName,info,context);
                try {
                    CreateFiles.savaFileToSD(fileName,info,context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    LogMe.d("读取出的数据",CreateFiles.readFromSD(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_phonebook_call:
                ed_phonebook_call.setText(ContactInfo.getCallInPhone(context));
                break;
            case R.id.tv_phonebook_sms:
                ed_phonebook_sms.setText(ContactInfo.getSmsInPhone(context));
                LogMe.d("短信内容",ContactInfo.getSmsInPhone(getApplication()));
                break;
            case R.id.left_img:
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.btn_tiao:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(flag){
            //获取的联系人号码的处理   getContact
            ed_phonebook.setText(ContactInfo.linkman(requestCode,data,getApplication()));
        }
        if(flagCamera){
            //图片处理
            dealImgResult(resultCode);
        }
        if (requestCode == RC_SETTINGS_SCREEN) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "returned_from_app_settings_to_activity", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * ====================================6.0 权限相关的处理=================================
     * */

    // 位置 相机 通讯录
    public void requestPermissions() {
        EasyPermissions.requestPermissions(this,
                "接下来需要获取WRITE_EXTERNAL_STORAGE和WRITE_EXTERNAL_STORAGE权限",
                R.string.setting,
                R.string.cancel,
                2,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_SMS);
    }
    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode){
            case 0:
                Toast.makeText(this, "已获取ACCESS_FINE_LOCATION权限", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "已获取CAMERA权限", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "已获取所有权限", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //处理权限名字字符串
        StringBuffer sb = new StringBuffer();
        for (String str : perms){
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2,sb.length(),"");
        switch (requestCode){
            case 0:
                Toast.makeText(this, "已拒绝权限" + perms.get(0), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "已拒绝权限" + perms.get(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "已拒绝WRITE_EXTERNAL_STORAGE和WRITE_EXTERNAL_STORAGE权限"+ perms.get(0), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "已拒绝所有权限"+ perms.get(0), Toast.LENGTH_SHORT).show();
                break;
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问" , Toast.LENGTH_SHORT).show();
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    /**
     * ============================================通讯录相关的=================================================
     * */
    //跳转到联系人列
    private void jumpOpen(){
        //需要判断是否获取通讯录权限
        if(EasyPermissions.hasPermissions(context,Manifest.permission.READ_CONTACTS)) {
            flag = true;
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("vnd.android.cursor.dir/phone");
            startActivityForResult(i, 0);
        }else{
            Toast.makeText(this, "没有通讯录权限", Toast.LENGTH_SHORT).show();
        }
    }
    /**
    /**
     * ======================================================定位相关的代码====================================================
     * */
    /**
     * 通过GPS获取定位信息
     */
    public void getGPSLocation() {
        Location gps = LocationUtils.getGPSLocation(this);
        if (gps == null) {
            //设置定位监听，因为GPS定位，第一次进来可能获取不到，通过设置监听，可以在有效的时间范围内获取定位信息
            LocationUtils.addLocationListener(context, LocationManager.GPS_PROVIDER, new LocationUtils.ILocationListener() {
                @Override
                public void onSuccessLocation(Location location) {
                    if (location != null) {
                        Toast.makeText(MainActivity.this, "gps onSuccessLocation location:  lat==" + location.getLatitude() + "     lng==" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "gps location is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "gps location: lat==" + gps.getLatitude() + "  lng==" + gps.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过网络等获取定位信息
     */
    private void getNetworkLocation() {
        Location net = LocationUtils.getNetWorkLocation(this);
        if (net == null) {
            Toast.makeText(this, "net location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "network location: lat==" + net.getLatitude() + "  lng==" + net.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 采用最好的方式获取定位信息
     */
    private void getBestLocation() {
        //先判断是否有定位权限
        if(EasyPermissions.hasPermissions(context,Manifest.permission.ACCESS_FINE_LOCATION)){
            Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
            c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
            c.setAltitudeRequired(true);//设置需要海拔
            c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
            c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
            //... Criteria 还有其他属性，就不一一介绍了
            Location best = LocationUtils.getBestLocation(this, c);
            if (best == null) {
                Toast.makeText(this, " best location is null", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "best location: lat==" + best.getLatitude() + " lng==" + best.getLongitude(), Toast.LENGTH_SHORT).show();
                ed_location.setText( best.getLatitude() +"      "+best.getLongitude());
            }
            try {
                //获取地址名称
                getAddress(best);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //请求谷歌API  根据经纬度来获取地址名称
//            getPresenter().getAddress(best.getLatitude()+","+best.getLongitude());
        }else{
            Toast.makeText(this, "没有定位权限", Toast.LENGTH_SHORT).show();
        }
    }

   //通过Geocoder 来获取地址名称信息  由于该操作属于耗时操作，在使用时需放到子线程中进行操作
    private void getAddress(Location location) throws IOException {
        Geocoder geocoder = new Geocoder(this);
        boolean falg = geocoder.isPresent();
        Log.e("xjp","the falg is " + falg);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //根据经纬度获取地理位置信息
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                //根据地址获取地理位置信息
//            List<Address> addresses = geocoder.getFromLocationName( "广东省珠海市香洲区沿河路321号", 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                stringBuilder.append(address.getCountryName()).append("_");//国家
                stringBuilder.append(address.getFeatureName()).append("_");//周边地址
                stringBuilder.append(address.getLocality()).append("_");//市
                stringBuilder.append(address.getPostalCode()).append("_");
                stringBuilder.append(address.getCountryCode()).append("_");//国家编码
                stringBuilder.append(address.getAdminArea()).append("_");//省份
                stringBuilder.append(address.getSubAdminArea()).append("_");
                stringBuilder.append(address.getThoroughfare()).append("_");//道路
                stringBuilder.append(address.getSubLocality()).append("_");//香洲区
                stringBuilder.append(address.getLatitude()).append("_");//经度
                stringBuilder.append(address.getLongitude());//维度
                System.out.println(stringBuilder.toString());
                tv_address.setText(stringBuilder.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(this, "报错", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

/*
* ============================================以下是多种方式对图片大小的处理==========================================================
* */
    private void getCamera() {
        //调取相机之前需要检查下，是否有权限
        if(EasyPermissions.hasPermissions(context,Manifest.permission.CAMERA)){
            flagCamera = true;
            file = new File(this.getExternalCacheDir(),"image.jpg");
            uri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.Images.Media.ORIENTATION,0);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(intent,3);
        }else{
            Toast.makeText(this, "请获取相机权限", Toast.LENGTH_SHORT).show();
        }
    }

 //结果的处理
    private void dealImgResult(int resultCode){
        if (resultCode == RESULT_OK ) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(),options);
                tv_yuantu.setText(bitmap.getByteCount()/1024+"");
                yuan_image.setImageBitmap(bitmap);
                //质量压缩图片
                bitmap = compressImage(bitmap,100);
                if(bitmap != null){
                    //显示图片
                    ya_image.setImageBitmap(bitmap);
                    //保持图片
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                        fos.flush();
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    /**
     * 将图片image压缩成大小为 size的图片（size表示图片大小，单位是KB）
     * */
    private Bitmap compressImage(Bitmap image,int size){
        //比例压缩
        int width = image.getWidth();
        int height = image.getHeight();
        //设置想要的大小
        int newWidth = 720;
        int newHeight = 800;
        //计算缩放比例
        float scaleWidth = ((float) newWidth)/width;
        float scaleHeight = ((float) newHeight)/height;
        //取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        //取得新的图片
        Bitmap bitmap = Bitmap.createBitmap(image,0,0,width,height,matrix,true);

        //图片质量压缩
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        int options = 100;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length/1024>size){
            // 重置baos即清空baos
            baos.reset();
            // 每次都减少10
            options -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG,options,baos);
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap1 = BitmapFactory.decodeStream(isBm,null,null);
        tv_yasuo.setText(baos.toByteArray().length/1024+"kb");
        return bitmap1;
    }
}


































package com.jyph.wsdapp.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.common.utils.phoneinfo.ContactInfo;
import com.jyph.wsdapp.common.utils.view.CommonPickerDialog;
import com.jyph.wsdapp.mvp.Presenter.BorrowInfoEmergencyPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowInfoEmergencyActivity extends BaseActivity<BorrowInfoEmergencyPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindViews({R.id.img_left,R.id.img_first,R.id.img_first_line,R.id.img_second,R.id.img_second_line,R.id.img_third,R.id.img_third_line,R.id.img_four,R.id.img_four_line,R.id.img_five})
    List<ImageView> imgViews;
    @BindViews({R.id.et_people_name,R.id.et_people_number,R.id.et_relation})
    List<EditText> editTexts;
    @BindView(R.id.btn_next)
    Button btnNext;

    private List<String> relationName = new ArrayList<String>();
    private String relationCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_emergency);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        imgViews.get(0).setImageResource(R.drawable.back);
        tvTitle.setText("填写借款资料");
        Collections.addAll(relationName, getResources().getStringArray(R.array.relation));
    }

    @Override
    public BorrowInfoEmergencyPresenter bindPresenter() {
        return super.bindPresenter();
    }

    @OnClick({R.id.img_left, R.id.et_people_number, R.id.et_relation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.et_people_number://调取通讯录
                jumpOpen();
                break;
            case R.id.et_relation:
                showSelectList(relationName);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editTexts.get(1).setText(ContactInfo.linkman(requestCode,data,getApplication()));
    }

    /**
     * ============================================通讯录相关的=================================================
     * */
    //跳转到联系人列
    private void jumpOpen(){
        //需要判断是否获取通讯录权限
        if(EasyPermissions.hasPermissions(getApplicationContext(), Manifest.permission.READ_CONTACTS)) {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("vnd.android.cursor.dir/phone");
            startActivityForResult(i, 0);
        }else{
            Toast.makeText(this, "没有通讯录权限", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 显示关系弹窗
     *
     * @param bankList
     */
    public void showSelectList(List<String> bankList) {
        final List<String> bankListBak = new ArrayList<String>(bankList);
        CommonPickerDialog configProDialog = new CommonPickerDialog(this, "",
                new CommonPickerDialog.onCommonSelectedListener() {
                    @Override
                    public void callBack(HashMap<Integer, String> map) {
                        // TODO Auto-generated method stub
                        int i = -1;
                        for (i = 0; i < bankList.size(); i++) {
                            if (map.get(0).equals(bankList.get(i))) {
                                break;
                            }
                        }
                        editTexts.get(2).setText(map.get(0));
                        editTexts.get(2).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_000000));
                        relationCode = relationName.get(i);
                        LogMe.d("jobCode", map.get(0) + "index:" + i);
                    }
                }, bankListBak);
        configProDialog.setDefaultSetting();
        configProDialog.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        configProDialog.getWindow().setLayout(getPhoneWidth(), getPhoneWidth() * 3 / 4);
        configProDialog.show();
    }




}

package com.jyph.wsdapp.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.jyph.wsdapp.mvp.presenter.BorrowInfoEmergencyPresenter;

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

    @BindViews({R.id.tv_title, R.id.et_people_number, R.id.et_relation})
    List<TextView> textViews;
    @BindViews({R.id.img_left, R.id.img_first, R.id.img_first_line, R.id.img_second, R.id.img_second_line, R.id.img_third, R.id.img_third_line, R.id.img_four, R.id.img_four_line, R.id.img_five})
    List<ImageView> imgViews;
    @BindView(R.id.et_people_name)
    EditText edPenpleNmae;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.view_line)
    View viewLine;

    private List<String> relationName = new ArrayList<String>();
    private String relationCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_emergency);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewLine.setVisibility(View.VISIBLE);
        imgViews.get(0).setImageResource(R.drawable.back);
        textViews.get(0).setText("填写借款资料");
        Collections.addAll(relationName, getResources().getStringArray(R.array.relation));

        textViews.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkNull();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkNull();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkNull();
            }
        });
        textViews.get(2).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkNull();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkNull();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkNull();
            }
        });
    }

    private void checkNull() {
        if (check()) {
            btnNext.setEnabled(true);
            btnNext.setBackgroundResource(R.drawable.btn_blue);
        } else {
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_gray);
        }
    }

    private boolean check() {
        if ("".equals(edPenpleNmae.getText().toString())) {
//            showSystemToast("联系人姓名不能为空");
            return false;
        }
        if ("".equals(textViews.get(1).getText().toString())) {
//            showSystemToast("联系电话不能为空");
            return false;
        }
        if ("".equals(textViews.get(2).getText().toString())) {
//            showSystemToast("和本人关系不能为空");
            return false;
        }
        return true;
    }

    @Override
    public BorrowInfoEmergencyPresenter bindPresenter() {
        return new BorrowInfoEmergencyPresenter(getApplicationContext());
    }

    @OnClick({R.id.img_left, R.id.et_people_number, R.id.et_relation, R.id.btn_next})
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
            case R.id.btn_next:
                startActivity(new Intent(this, BorrowInfoGuideActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textViews.get(1).setText(ContactInfo.linkman(requestCode, data, getApplication()));
    }

    /**
     * ============================================通讯录相关的=================================================
     */
    //跳转到联系人列
    private void jumpOpen() {
        //需要判断是否获取通讯录权限
        if (EasyPermissions.hasPermissions(getApplicationContext(), Manifest.permission.READ_CONTACTS)) {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("vnd.android.cursor.dir/phone");
            startActivityForResult(i, 0);
        } else {
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
                        textViews.get(2).setText(map.get(0));
                        textViews.get(2).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_000000));
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

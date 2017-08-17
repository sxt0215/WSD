package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.common.utils.view.CommonPickerDialog;
import com.jyph.wsdapp.mvp.Presenter.BorrowInfoCardPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/15.
 */

public class BorrowInfoCardActivity extends BaseActivity<BorrowInfoCardPresenter> {
    @BindViews({R.id.img_left,R.id.img_card_first,R.id.img_card_second,R.id.img_card_third})
    List<ImageView> imageViews;
    @BindViews({R.id.tv_title,R.id.tv_card_first,R.id.tv_card_second,R.id.tv_card_third ,R.id.tv_occupation ,R.id.tv_education ,R.id.tv_marital ,R.id.tv_house_type})
    List<TextView> textViews;
    @BindView(R.id.btn_next)
    Button btnNext;

    private String strFrontW = "身份证正面扫描<font color='#ffb319'>（未完成）</font>",
            strFrontY = "身份证正面扫描<font color='#4cd194'>（已完成）</font>",

            strBehindW = "身份证后面扫描<font color='#ffb319'>（未完成）</font>",
            strBehindY = "身份证后面扫描<font color='#4cd194'>（已完成）</font>",

            strFaceW = "身份证后面扫描<font color='#ffb319'>（未完成）</font>",
            strFaceY = "身份证后面扫描<font color='#4cd194'>（已完成）</font>";
    private List<String> jobNameList = new ArrayList<String>(),
            edLevelList = new ArrayList<String>(),
            marriageList = new ArrayList<String>(),
            hukouList = new ArrayList<String>();

    private String jobCode,edLevelCode,marriageCode,hukouCode;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        imageViews.get(0).setImageResource(R.drawable.back);
        textViews.get(0).setText(R.string.fill_data);
        textViews.get(1).setText(Html.fromHtml(strFrontW));
        textViews.get(2).setText(Html.fromHtml(strBehindY));
        textViews.get(3).setText(Html.fromHtml(strFaceW));

        Collections.addAll(jobNameList, getResources().getStringArray(R.array.job_name));
        Collections.addAll(edLevelList, getResources().getStringArray(R.array.ed_level));
        Collections.addAll(marriageList, getResources().getStringArray(R.array.marriage));
        Collections.addAll(hukouList, getResources().getStringArray(R.array.hukou));

        if (check()){
            btnNext.setEnabled(true);
            btnNext.setBackgroundResource(R.drawable.btn_blue);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(),BorrowInfoEmergencyActivity.class));
                }
            });
        }else{
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_gray);
        }
    }

    @Override
    public BorrowInfoCardPresenter bindPresenter() {
        return super.bindPresenter();
    }


    @OnClick({R.id.img_left, R.id.img_card_first, R.id.img_card_second, R.id.img_card_third, R.id.tv_occupation, R.id.tv_education, R.id.tv_marital, R.id.tv_house_type, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.img_card_first:
                break;
            case R.id.img_card_second:
                break;
            case R.id.img_card_third:
                break;
            case R.id.tv_occupation://
                showSelectList(jobNameList,"当前职业",4);
                break;
            case R.id.tv_education://教育程度
                showSelectList(edLevelList,"教育程度",5);
                break;
            case R.id.tv_marital://婚姻状况
                showSelectList(marriageList,"婚姻状况",6);
                break;
            case R.id.tv_house_type://户口类型
                showSelectList(hukouList,"户口类型",7);
                break;
        }
    }

    private boolean check() {
        if ("".equals(jobCode)) {
            showSystemToast("请选择当前职位");
            return false;
        }
        if ("".equals(edLevelCode)) {
            showSystemToast("请选择教育程度");
            return false;
        }
        if ("".equals(marriageCode)) {
            showSystemToast("请选择婚姻状态");
            return false;
        }
        if ("".equals(hukouCode)) {
            showSystemToast("请选择户口类型");
            return false;
        }
        return true;
    }

    /**
     * 显示列表弹窗
     *
     * @param bankList 银行卡 list
     */
    public void showSelectList(List<String> bankList,String title, int num) {// 4 5 6 7
        final List<String> bankListBak = new ArrayList<String>(bankList);
        CommonPickerDialog configProDialog = new CommonPickerDialog(this, title,
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
                        textViews.get(num).setText(title+":"+map.get(0));
                        textViews.get(num).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_000000));
                        if(num == 4){
                            jobCode = jobNameList.get(i);
                        }else if(num == 5){
                            edLevelCode = edLevelList.get(i);
                        }else if(num == 6){
                            marriageCode = marriageList.get(i);
                        }else if(num == 7){
                            hukouCode = hukouList.get(i);
                        }
                        LogMe.d("jobCode", map.get(0) + "index:" + i);
                    }
                }, bankListBak);
        configProDialog.setDefaultSetting();
        configProDialog.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        configProDialog.getWindow().setLayout(getPhoneWidth(), getPhoneWidth() * 3 / 4);
        configProDialog.show();
    }

}

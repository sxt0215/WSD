package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.application.MyApplicationLike;
import com.jyph.wsdapp.common.sharepreference.MySharePreference;
import com.jyph.wsdapp.mvp.presenter.BorrowInfoGuidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/15.
 */

public class BorrowInfoGuideActivity extends BaseActivity<BorrowInfoGuidePresenter> {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindViews({R.id.tv_title,R.id.tv_identity_state,R.id.tv_emergency_state,R.id.tv_otherinfo_state,R.id.tv_undetermined_state})
    List<TextView> textViews;
    @BindViews({R.id.lin_identity,R.id.lin_emergency,R.id.lin_otherinfo,R.id.lin_undetermined})
    List<LinearLayout> linearLayouts;
    @BindView(R.id.btn_bind_card)
    Button btnBindCard;
    MySharePreference mSharePreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_guide);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        imgLeft.setImageResource(R.drawable.back);
        textViews.get(0).setText(R.string.fill_data);
        mSharePreference = MyApplicationLike.getInstance().getSharePreference();
        checkState();
    }

    private void checkState(){
        if(mSharePreference.isIdentity()){//true  已填写
            textViews.get(1).setText(R.string.yi_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.gray_969696));
        }else{
            textViews.get(1).setText(R.string.wei_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.red_f74646));
        }
        if(mSharePreference.isEmergency()){//true  已填写
            textViews.get(1).setText(R.string.yi_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.gray_969696));
        }else{
            textViews.get(1).setText(R.string.wei_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.red_f74646));
        }
        if(mSharePreference.isOtherInfo()){//true  已填写
            textViews.get(1).setText(R.string.yi_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.gray_969696));
        }else{
            textViews.get(1).setText(R.string.wei_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.red_f74646));
        }
        if(mSharePreference.isUndetermined()){//true  已填写
            textViews.get(1).setText(R.string.yi_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.gray_969696));
        }else{
            textViews.get(1).setText(R.string.wei_fill);
            textViews.get(1).setTextColor(this.getResources().getColor(R.color.red_f74646));
        }
    }

    @OnClick({R.id.img_left, R.id.lin_identity, R.id.lin_emergency, R.id.lin_otherinfo, R.id.lin_undetermined, R.id.btn_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.lin_identity://身份信息  进到身份证扫描页面
                startActivity(new Intent(this,BorrowInfoCardActivity.class));
                break;
            case R.id.lin_emergency://紧急联系人
                startActivity(new Intent(this,BorrowInfoEmergencyActivity.class));
                break;
            case R.id.lin_otherinfo://其他信息
                break;
            case R.id.lin_undetermined://待定
                break;
            case R.id.btn_bind_card://绑卡借款
                startActivity(new Intent(this,BorrowInfoBindCardActivity.class));
                break;
        }
    }

    @Override
    public BorrowInfoGuidePresenter bindPresenter() {
        return new BorrowInfoGuidePresenter(getApplicationContext());
    }
}

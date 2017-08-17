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
import com.jyph.wsdapp.mvp.Presenter.BorrowInfoGuidePresenter;

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
    @BindViews({R.id.tv_title,R.id.tv_identity,R.id.tv_emergency,R.id.tv_otherinfo,R.id.tv_undetermined})
    List<TextView> textViews;
    @BindView(R.id.btn_bind_card)
    Button btnBindCard;

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



    }

    @OnClick({R.id.img_left, R.id.tv_identity, R.id.tv_emergency, R.id.tv_otherinfo, R.id.tv_undetermined, R.id.btn_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_identity://身份信息  进到身份证扫描页面
                startActivity(new Intent(this,BorrowInfoCardActivity.class));
                break;
            case R.id.tv_emergency://紧急联系人
                startActivity(new Intent(this,BorrowInfoEmergencyActivity.class));
                break;
            case R.id.tv_otherinfo://其他信息
                break;
            case R.id.tv_undetermined://待定
                break;
            case R.id.btn_bind_card://绑卡借款
                startActivity(new Intent(this,BorrowInfoBindCardActivity.class));
                break;
        }
    }

    @Override
    public BorrowInfoGuidePresenter bindPresenter() {
        return super.bindPresenter();
    }
}

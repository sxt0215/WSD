package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.mvp.Presenter.BorrowInfoBindCardPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowInfoBindCardActivity extends BaseActivity<BorrowInfoBindCardPresenter> {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.btn_bind_card)
    Button btnBindCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_bindcard);
        ButterKnife.bind(this);
    }

    @Override
    public BorrowInfoBindCardPresenter bindPresenter() {
        return super.bindPresenter();
    }

    @OnClick({R.id.img_left, R.id.btn_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.btn_bind_card:
                startActivity(new Intent(this,BorrowMoneyActivity.class));
                break;
        }
    }
}

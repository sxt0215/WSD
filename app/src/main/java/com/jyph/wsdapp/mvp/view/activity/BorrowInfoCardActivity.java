package com.jyph.wsdapp.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.mvp.Presenter.BorrowInfoCardPresenter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/15.
 */

public class BorrowInfoCardActivity extends BaseActivity<BorrowInfoCardPresenter> {
    @BindViews({R.id.img_left,R.id.img_card_first,R.id.img_card_second,R.id.img_card_third})
    ImageView imgLeft,imgCardFirst,imgCardSecond,imgCardThird;
    @BindViews({R.id.tv_title,R.id.tv_card_first,R.id.tv_card_second,R.id.tv_card_third ,R.id.tv_occupation ,R.id.tv_education ,R.id.tv_marital ,R.id.tv_house_type})
    TextView tvTitle,tvCardFirst,tvCardSecond,tvCardThird,tvOccupation,tvEducation,tvMarital,tvHouseType;
    @BindView(R.id.btn_next)
    Button btnNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_info_1);
        ButterKnife.bind(this);
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
            case R.id.tv_occupation:
                break;
            case R.id.tv_education:
                break;
            case R.id.tv_marital:
                break;
            case R.id.tv_house_type:
                break;
            case R.id.btn_next:

                break;
        }
    }
}

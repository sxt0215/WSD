package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.view.CarouselPicker;
import com.jyph.wsdapp.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/28.
 */

public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.textCarousel)
    CarouselPicker textCarousel;
    @BindView(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @BindView(R.id.img_arrow_right)
    ImageView imgArrowRight;
    @BindViews({R.id.btn_five_days, R.id.btn_ten_days, R.id.btn_fifteen_days})
    List<Button> buttons;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindViews({R.id.lin_borrow,R.id.lin_borrow_success})
    List<LinearLayout> linearLayouts;


    private boolean isLogin,bindCard ,borrowSucess;
    private int days, money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("微闪贷");
        viewLine.setVisibility(View.VISIBLE);
        imgLeft.setVisibility(View.GONE);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setImageResource(R.drawable.right_card);
        borrowType();
    }

    private void borrowType() {
        if (borrowSucess) {//借款成功
            /**
             * 审核和未审核两种情况  需要区分下  文案不同
             * */
            linearLayouts.get(0).setVisibility(View.GONE);
            linearLayouts.get(1).setVisibility(View.VISIBLE);
        } else {//未借款
            linearLayouts.get(0).setVisibility(View.VISIBLE);
            linearLayouts.get(1).setVisibility(View.GONE);
            initMoney();
            btnDaysChange();
        }
    }

    private void btnDaysChange() {

        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money == 900) {
                    buttons.get(0).setBackgroundResource(R.drawable.btn_blue);
                    buttons.get(0).setTextColor(getResources().getColor(R.color.white_ffffff));
                    buttons.get(1).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(1).setTextColor(getResources().getColor(R.color.black_000000));
//                    buttons.get(2).setBackgroundResource(R.drawable.btn_white);
//                    buttons.get(2).setTextColor(getResources().getColor(R.color.black_000000));
                } else {
                    buttons.get(0).setBackgroundResource(R.drawable.btn_blue);
                    buttons.get(0).setTextColor(getResources().getColor(R.color.white_ffffff));
                    buttons.get(1).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(1).setTextColor(getResources().getColor(R.color.black_000000));
                    buttons.get(2).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(2).setTextColor(getResources().getColor(R.color.black_000000));
                }
                days = 5;
            }
        });
        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money == 900) {
                    buttons.get(1).setBackgroundResource(R.drawable.btn_blue);
                    buttons.get(1).setTextColor(getResources().getColor(R.color.white_ffffff));
                    buttons.get(0).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(0).setTextColor(getResources().getColor(R.color.black_000000));
//                    buttons.get(2).setBackgroundResource(R.drawable.btn_white);
//                    buttons.get(2).setTextColor(getResources().getColor(R.color.black_000000));
                } else {
                    buttons.get(1).setBackgroundResource(R.drawable.btn_blue);
                    buttons.get(1).setTextColor(getResources().getColor(R.color.white_ffffff));
                    buttons.get(0).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(0).setTextColor(getResources().getColor(R.color.black_000000));
                    buttons.get(2).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(2).setTextColor(getResources().getColor(R.color.black_000000));
                }
                days = 10;
            }
        });
        buttons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons.get(2).setBackgroundResource(R.drawable.btn_blue);
                buttons.get(2).setTextColor(getResources().getColor(R.color.white_ffffff));
                buttons.get(1).setBackgroundResource(R.drawable.btn_white);
                buttons.get(1).setTextColor(getResources().getColor(R.color.black_000000));
                buttons.get(0).setBackgroundResource(R.drawable.btn_white);
                buttons.get(0).setTextColor(getResources().getColor(R.color.black_000000));
                days = 15;
            }
        });

    }

    private void initMoney() {
        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
        textItems.add(new CarouselPicker.TextItem("100", 9));
        textItems.add(new CarouselPicker.TextItem("200", 9));
        textItems.add(new CarouselPicker.TextItem("300", 9));
        textItems.add(new CarouselPicker.TextItem("400", 9));
        textItems.add(new CarouselPicker.TextItem("500", 9));
        textItems.add(new CarouselPicker.TextItem("600", 9));
        textItems.add(new CarouselPicker.TextItem("700", 9));
        textItems.add(new CarouselPicker.TextItem("800", 9));
        textItems.add(new CarouselPicker.TextItem("900", 9));
        textItems.add(new CarouselPicker.TextItem("1000", 9));
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this, textItems, 0);
        textCarousel.setAdapter(textAdapter);
        textCarousel.setCurrentItem(2);
        textCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                money = Integer.parseInt(textItems.get(position).getText().toString());
                showSystemToast("Selected item in text carousel is  : " + textItems.get(position).getText());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (money == 900) {
                    buttons.get(2).setBackgroundResource(R.drawable.btn_gray);
                    buttons.get(2).setTextColor(getResources().getColor(R.color.gray_969696));
                    buttons.get(2).setEnabled(false);
                } else {
                    buttons.get(2).setBackgroundResource(R.drawable.btn_white);
                    buttons.get(2).setTextColor(getResources().getColor(R.color.black_000000));
                    buttons.get(2).setEnabled(true);
                }
            }
        });
    }

    @Override
    public MainPresenter bindPresenter() {
        return new MainPresenter(this);
    }

    @OnClick({R.id.img_right, R.id.img_arrow_left, R.id.img_arrow_right, R.id.btn_borrow_money, R.id.img_tie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_right:
                showSystemToast("跳转到设置页面");
                break;
            case R.id.img_tie:
                showSystemToast("跳转到提额页面");
                break;
            case R.id.img_arrow_left:
                textCarousel.arrowScroll(1);
                break;
            case R.id.img_arrow_right:
                textCarousel.arrowScroll(2);
                break;
            case R.id.btn_borrow_money:
                /**
                 * 先判断是否登录
                 * 未登录----去登录
                 * 登录 -----1）判断资料是否完善好
                 *           2）判断是首次借款还是二次借款
                 * */
                if (!isLogin) {//登录   把天数和money带着跳转
                    if(bindCard){//完善资料信息

                    }else{//未完善资料    进到资料引导页完善信息
                        startActivity(new Intent(this, BorrowInfoGuideActivity.class));
                    }
                } else {//未登录
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }


}

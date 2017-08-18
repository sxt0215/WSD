package com.jyph.wsdapp.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.view.CarouselPicker;
import com.jyph.wsdapp.mvp.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sxt_0 on 2017/8/7.
 */

public class HomeActivity extends BaseActivity<HomePresenter> {
    private CarouselPicker textCarousel;
    private TextView tvSelectedItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView(){
        textCarousel = (CarouselPicker) findViewById(R.id.textCarousel);
        tvSelectedItem = (TextView) findViewById(R.id.tvSelectedItem);

        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
        textItems.add(new CarouselPicker.TextItem("100", 10));
        textItems.add(new CarouselPicker.TextItem("200", 10));
        textItems.add(new CarouselPicker.TextItem("300", 10));
        textItems.add(new CarouselPicker.TextItem("400", 10));
        textItems.add(new CarouselPicker.TextItem("500", 10));
        textItems.add(new CarouselPicker.TextItem("600", 10));
        textItems.add(new CarouselPicker.TextItem("700", 10));
        textItems.add(new CarouselPicker.TextItem("800", 10));
        textItems.add(new CarouselPicker.TextItem("900", 10));
        textItems.add(new CarouselPicker.TextItem("1000", 10));
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this, textItems, 0);
        textCarousel.setAdapter(textAdapter);

        textCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvSelectedItem.setText("Selected item in text carousel is  : "+textItems.get(position).getText());
                //textItems.get(position).getText()
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}

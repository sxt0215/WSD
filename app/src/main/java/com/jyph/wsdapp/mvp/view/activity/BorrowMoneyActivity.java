package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.mvp.presenter.BorrowMoneyPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowMoneyActivity extends BaseActivity<BorrowMoneyPresenter> {


    @BindViews({R.id.img_left, R.id.img_first, R.id.img_first_line, R.id.img_second, R.id.img_second_line, R.id.img_third,
            R.id.img_third_line, R.id.img_four, R.id.img_four_line, R.id.img_five, R.id.img_id_alter, R.id.img_call_alter})
    List<ImageView> imageViews;
    @BindViews({R.id.tv_title, R.id.tv_cardof_name, R.id.tv_id_number, R.id.tv_card_name, R.id.btn_get_code,})
    List<TextView> textViews;
    @BindViews({R.id.ed_card_number, R.id.ed_call_number, R.id.ed_sms_code})
    List<EditText> editTexts;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.lin_agree)
    LinearLayout linAgree;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.view_line)
    View viewLine;
    private CountDownTask task;

    private String cardName, idNumber, choseBank, bankNumber, bankCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_money);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewLine.setVisibility(View.VISIBLE);
        imageViews.get(0).setImageResource(R.drawable.back);
        textViews.get(0).setText(R.string.fill_data);
        getIntents();
        textViews.get(1).setText(cardName);
        textViews.get(2).setText(idNumber);
        textViews.get(3).setText(choseBank + ":");
        //4位空格 2345 2345 5467 7896
        editTexts.get(0).setText(bankNumber.substring(0, 4) + " " + bankNumber.substring(4, 8)+" "
                + bankNumber.substring(8, 12)+" "+bankNumber.substring(12,bankNumber.length()));
//        editTexts.get(0).setText(bankNumber);
        editTexts.get(0).setInputType(InputType.TYPE_NULL);
        editTexts.get(1).setText(bankCall);
        editTexts.get(1).setInputType(InputType.TYPE_NULL);

        startCounting();
    }


    @Override
    public BorrowMoneyPresenter bindPresenter() {
        return new BorrowMoneyPresenter(getApplicationContext());
    }

    private void getIntents() {
        cardName = getIntent().getStringExtra("cardName");
        idNumber = getIntent().getStringExtra("idNumber");
        choseBank = getIntent().getStringExtra("choseBank");
        bankNumber = getIntent().getStringExtra("bankNumber");
        bankCall = getIntent().getStringExtra("bankCall");
    }

    private void alterInfo() {
        Intent intent = new Intent(this, BorrowInfoBindCardActivity.class);
        intent.putExtra("source", true);
        intent.putExtra("cardName", cardName);
        intent.putExtra("idNumber", idNumber);
        intent.putExtra("choseBank", choseBank);
        intent.putExtra("bankNumber", bankNumber);
        intent.putExtra("bankCall", bankCall);
        this.startActivity(intent);
    }

    @OnClick({R.id.img_left, R.id.img_id_alter, R.id.img_call_alter, R.id.btn_get_code, R.id.lin_agree, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                startActivity(new Intent(this,BorrowInfoGuideActivity.class));
                finish();
                break;
            case R.id.img_id_alter://修改卡号
                alterInfo();
                break;
            case R.id.img_call_alter://修改电话
                alterInfo();
                break;
            case R.id.btn_get_code:
                //getPresenter().getSmsCode("手机号");
                startCounting();
                break;
            case R.id.lin_agree:
                break;
            case R.id.btn_next:

                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (task != null && !task.isCancelled())
            task.cancel(true);
    }

    public void startCounting() {
        task = new CountDownTask();
        task.execute();
    }

    /**
     * 60s 倒计时
     */
    public class CountDownTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            Log.e("TASk", "preexecute");
            textViews.get(4).setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            textViews.get(4).setEnabled(true);
            textViews.get(4).setText(getResources().getString(R.string.resend));
            Log.e("TASk", "postexecute");
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("TASk", "doinbackground");
            for (int i = 60; i >= 0; i--) {
                try {
                    System.out.println("---------" + i);
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e("TASk", "onprogressupdate");
            textViews.get(4).setText(values[0] + "s");
            super.onProgressUpdate(values);
        }
    }

}

package com.jyph.wsdapp.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.common.utils.view.CommonPickerDialog;
import com.jyph.wsdapp.mvp.presenter.BorrowInfoBindCardPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt_0 on 2017/8/16.
 */

public class BorrowInfoBindCardActivity extends BaseActivity<BorrowInfoBindCardPresenter> {
    @BindViews({R.id.img_left, R.id.img_first, R.id.img_first_line, R.id.img_second, R.id.img_second_line
            , R.id.img_third, R.id.img_third_line, R.id.img_four, R.id.img_four_line, R.id.img_five})
    List<ImageView> imageViews;
    @BindView(R.id.btn_bind_card)
    Button btnBindCard;
    @BindViews({R.id.tv_title, R.id.textView})
    List<TextView> textViews;
    @BindViews({R.id.et_card_name, R.id.et_id_number, R.id.et_choose_bank, R.id.et_bank_number, R.id.et_bank_call})
    List<EditText> editTexts;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.view_line)
    View viewLine;
    private String cardName, idNumber, choseBank, bankNumber, bankCall;
    private boolean source;

    private List<String> bankNameList = new ArrayList<String>();
    private List<String> bankCodeList = new ArrayList<String>();
    private String bankCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowinfo_bindcard);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewLine.setVisibility(View.VISIBLE);
        imageViews.get(0).setImageResource(R.drawable.back);
        textViews.get(0).setText(R.string.fill_data);
        getIntents();
        editTexts.get(2).setInputType(InputType.TYPE_NULL);
        Collections.addAll(bankNameList, getResources().getStringArray(R.array.bank_name));
        Collections.addAll(bankCodeList, getResources().getStringArray(R.array.bank_code));

        if (source) {//true  从下一页点击修改过来
            editTexts.get(0).setText(cardName);
            editTexts.get(0).setInputType(InputType.TYPE_NULL);
            editTexts.get(0).setTextColor(getResources().getColor(R.color.gray_969696));
            editTexts.get(1).setText(idNumber);
            editTexts.get(1).setInputType(InputType.TYPE_NULL);
            editTexts.get(1).setTextColor(getResources().getColor(R.color.gray_969696));
            editTexts.get(2).setText(choseBank);
            editTexts.get(3).setText(bankNumber);
            editTexts.get(4).setText(bankCall);
        }
    }

    private void getIntents() {
        source = getIntent().getBooleanExtra("source", false);
        cardName = getIntent().getStringExtra("cardName");
        idNumber = getIntent().getStringExtra("idNumber");
        choseBank = getIntent().getStringExtra("choseBank");
        bankNumber = getIntent().getStringExtra("bankNumber");
        bankCall = getIntent().getStringExtra("bankCall");
    }

    private boolean check() {
        cardName = editTexts.get(0).getText().toString();
        idNumber = editTexts.get(1).getText().toString();
        choseBank = editTexts.get(2).getText().toString();
        bankNumber = editTexts.get(3).getText().toString();
        bankCall = editTexts.get(4).getText().toString();
        if ("".equals(cardName)) {
            showSystemToast("姓名不能为空");
            return false;
        }
        if ("".equals(idNumber)) {
            showSystemToast("身份证号不能为空");
            return false;
        }
        if ("".equals(choseBank)) {
            showSystemToast("银行不能为空");
            return false;
        }
        if ("".equals(bankNumber)) {
            showSystemToast("银行卡号不能为空");
            return false;
        }
        if ("".equals(bankCall)) {
            showSystemToast("手机号不能为空");
            return false;
        }
        if (checkbox.isChecked()) {
            showSystemToast("请同意协议");
            return false;
        }
        return true;
    }

    @Override
    public BorrowInfoBindCardPresenter bindPresenter() {
        return new BorrowInfoBindCardPresenter(getApplicationContext());
    }

    @OnClick({R.id.img_left, R.id.btn_bind_card,R.id.et_choose_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.btn_bind_card:
                if (check()) {
                    Intent intent = new Intent(this, BorrowMoneyActivity.class);
                    intent.putExtra("cardName", cardName);
                    intent.putExtra("idNumber", idNumber);
                    intent.putExtra("choseBank", choseBank);
                    intent.putExtra("bankNumber", bankNumber);
                    intent.putExtra("bankCall", bankCall);
                    this.startActivity(intent);
                    finish();
                }
                break;
            case R.id.et_choose_bank://弹出银行列表
                showBankList(bankNameList);
                break;
        }
    }

    /**
     * 显示银行列表弹窗
     *
     * @param bankList
     */
    public void showBankList(List<String> bankList) {
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
                        bankCode = bankCodeList.get(i);
                        LogMe.d("jobCode", map.get(0) + "index:" + i);
                    }
                }, bankListBak);
        configProDialog.setDefaultSetting();
        configProDialog.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        configProDialog.getWindow().setLayout(getPhoneWidth(), getPhoneWidth() * 3 / 4);
        configProDialog.show();
    }


}

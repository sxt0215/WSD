package com.jyph.wsdapp.common.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jyph.wsdapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 非联动N选择框Dialog
 * 
 * @author KartyJoin
 * 
 *         CommonPickerDialog.java - 2015-11-13
 * 
 */
public class CommonPickerDialog extends Dialog {

	private Context context;
	private HashMap<Integer, String> resultMap;// 结果集 1=第一个选择内容
	private LinearLayout ll_container;
	private int pickerCount;// 选择框数量
	private List<String>[] list;
	private String hintStr;// 标题提示内容
	private List<TimePickerView> viewList;// 选择器引用容器
	private onCommonSelectedListener mListener;// 数据回调
	private TextView tv_choose;// 标题

	private LinearLayout layout_dialog;

	/**
	 * 
	 * @param context
	 * @param hint
	 *            提示信息
	 * @param listener
	 *            0-n get
	 * @param list
	 */
	public CommonPickerDialog(Context context, String hint ,
                              onCommonSelectedListener listener, List<String>... list) {
		super(context, R.style.TimePickerDialog);
		this.context = context;
		this.pickerCount = list.length;
		this.list = list;
		if (hint == null) {
			hintStr = "";
		} else {
			hintStr = hint;
		}
		this.mListener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	/* 初始化时间选择Dialog */
	private void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_common_picker, null);


		initData(view);
		setListener(view);
		setContentView(view);
	}


	private void initData(View view) {
		resultMap = new HashMap<Integer, String>();
		tv_choose = (TextView) view.findViewById(R.id.tv_chose_title);
		for (int i = 0; i < pickerCount; i++) {// 添加空字符，防止无限滚动
			list[i].add("");
			list[i].add("");
			list[i].add("");
			list[i].add("");
		}

		ll_container = (LinearLayout) view
				.findViewById(R.id.dialog_picker_container);// picker view 容器

		addPickerView();// 填充picker

		tv_choose.setText(hintStr);
	}

	public TextView getTv_choose() {
		return tv_choose;
	}

	public void setListener(View view) {
//		Button comfirm = (Button) view.findViewById(R.id.btn_confirm);
//		Button cancel = (Button) view.findViewById(R.id.btn_cancel);
		TextView comfirm = (TextView) view.findViewById(R.id.tv_sure);
		TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);

		comfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < pickerCount; i++) {
					if (TextUtils.isEmpty(viewList.get(i).getCurrentText())) {
						Toast.makeText(context, "您第" + (i + 1) + "个选择框中未选择",
								Toast.LENGTH_SHORT).show();
						CommonPickerDialog.this.dismiss();
						return;
					}
					resultMap.put(i, viewList.get(i).getCurrentText());
				}
				mListener.callBack(resultMap);
				CommonPickerDialog.this.dismiss();
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonPickerDialog.this.dismiss();
			}
		});
	}

	public interface onCommonSelectedListener {
		void callBack(HashMap<Integer, String> map);
	}

	/**
	 * 默认下方弹出设置
	 */
	public void setDefaultSetting() {
		this.setCanceledOnTouchOutside(true);
		// 保证Dialog对象的宽度是全屏的宽度
		Window win1 = this.getWindow();
		win1.getDecorView().setPadding(0, 0, 0, 0);
		LayoutParams dateLp = win1.getAttributes();
		dateLp.width = LayoutParams.MATCH_PARENT;
		dateLp.height = LayoutParams.WRAP_CONTENT;
		win1.setAttributes(dateLp);
		win1.getDecorView().setBackgroundColor(Color.WHITE);
		// Dialog在底部展示
		win1.setGravity(Gravity.BOTTOM);

		win1.setGravity(Gravity.BOTTOM);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		LayoutParams params = this.getWindow().getAttributes();
		params.width = width - DisplayUtil.dip2px(context, 10);
		this.getWindow().setAttributes(params);
	}

	private TimePickerView pv;
	/**
	 * 动态添加PickerView
	 */
	private void addPickerView() {
		viewList = new ArrayList<TimePickerView>();
		ll_container.removeAllViews();
		for (int i = 0; i < pickerCount; i++) {
			ViewGroup v = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.dialog_common_picker_item, ll_container, false);
			// 删除条目
			pv = (TimePickerView) v
					.findViewById(R.id.picker_view);
			pv.setTag(i);
			pv.setData(list[i]);
			if (list[i].size() > 0)
				pv.setCurrentText(list[i].get(0));
			viewList.add(pv);
			v.removeAllViews();
			ll_container.addView(pv);
		}
	}

}

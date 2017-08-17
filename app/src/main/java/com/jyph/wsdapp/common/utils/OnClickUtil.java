package com.jyph.wsdapp.common.utils;

/**
 * 连击事件判断
 * Created by sxt_0 on 2017/8/2.
 */
public class OnClickUtil {

	private static long lastClickTime;

	public static boolean isFastDoubleClick(int mills) {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < mills) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

}

package com.example.autosms.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Toast显示工具类
 * 
 *
 */
public class ToastTool {

	/**
	 * 根据自己的时间去定义一个Toast 输入时间为毫秒
	 * 
	 * @param c
	 * @param info
	 * @param time
	 */
	public static void setToatBytTime(Context c, String info, int time) {
		final Toast toast = Toast.makeText(c, info, Toast.LENGTH_SHORT);
		toast.show();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				toast.cancel();
			}
		}, time);
	}

	/**
	 * 显示一个2秒的Toast
	 */
	public static void showShortToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示一个长的Toast
	 * 
	 * @param context
	 * @param text
	 */
	public static void showLongToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}

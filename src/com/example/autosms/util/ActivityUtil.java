package com.example.autosms.util;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityUtil {

	/**
	 * 页面跳转不带值跳转
	 * 
	 * @param context
	 * @param clazz
	 */
	public static void jumpActivity(Context context,
			@SuppressWarnings("rawtypes") Class clazz) {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}
	
	/**
	 * 页面跳转带Bundle跳转
	 * 
	 * @param context
	 * @param clazz
	 */
	public static void jumpActivity(Context context,Bundle bundle,
			@SuppressWarnings("rawtypes") Class clazz) {
		Intent intent = new Intent(context, clazz);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	/**
	 * activity带值跳转
	 * 
	 * @param context
	 * @param clazz
	 * @param obj
	 */
	public static void jumpActivityWithData(Context context,
			@SuppressWarnings("rawtypes") Class clazz, String key,
			Serializable value) {
		Intent intent = new Intent(context, clazz);
		Bundle bundle = new Bundle();
		bundle.putSerializable(key, value);
		context.startActivity(intent);
	}
}

package com.example.autosms;

import com.example.autosms.util.SPUtils;
import com.example.autosms.util.StrUtil;
import com.example.autosms.util.ToastTool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * 设置界面
 *
 */
public class SetActivity extends Activity {
	private EditText phoneNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.set);
		phoneNum = (EditText) findViewById(R.id.phoneNum);// 拿到电话号码
	}

	/**
	 * 按钮点击事件
	 */
	public void click(View view) {
		String pStr = phoneNum.getText().toString().trim();
		if (StrUtil.isEmpty(pStr)) {
			ToastTool.showShortToast(SetActivity.this, "请输入号码");
			return;
		}

		// 设置转发的号码
		SPUtils.put(SetActivity.this, "strNo", pStr);
		ToastTool.showShortToast(SetActivity.this, "设置成功");
		finish();
	}
}

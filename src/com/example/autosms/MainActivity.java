package com.example.autosms;

import com.example.autosms.util.ActivityUtil;
import com.example.autosms.util.SPUtils;
import com.example.autosms.util.StrUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 
 * 主界面
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

	}

	/**
	 * 按钮点击事件
	 */
	public void click(View view) {
		switch (view.getId()) {
		case R.id.find:
			ActivityUtil.jumpActivity(MainActivity.this, MsgActivity.class);
			break;
		case R.id.set:
			ActivityUtil.jumpActivity(MainActivity.this, SetActivity.class);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		String mes = (String) SPUtils.get(MainActivity.this, "message", "3");
		if (!StrUtil.isEmpty(mes)) {
			if (mes.equals("2")) {
				ActivityUtil.jumpActivity(MainActivity.this, SMSActivity.class);
			}
		}
	}
}

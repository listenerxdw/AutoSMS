package com.example.autosms;

import com.example.autosms.util.SPUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * 
 * 短信接收界面
 *
 */
public class SMSActivity extends Activity {
	private TextView smsFrom;// 手机号码
	private TextView smsContext;// 短信内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置无标题
		setContentView(R.layout.sms);// 加载界面
		smsFrom = (TextView) findViewById(R.id.smsFrom);// 拿到界面上的手机号
		smsContext = (TextView) findViewById(R.id.smsContext);// 拿到短信内容
		Intent intent = getIntent();// 获取数据
		SPUtils.put(SMSActivity.this, "message", "1");// 判断如果进入，则修改状态
		if (intent != null) {
			String from = (String) SPUtils.get(SMSActivity.this, "from", "");//获取收到的短信的内容
			String context = (String) SPUtils.get(SMSActivity.this, "context", "");
			smsFrom.setText("发信人:" + from);
			smsContext.setText(context);//设置内容
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		SPUtils.put(SMSActivity.this, "message", "1");
	}
}

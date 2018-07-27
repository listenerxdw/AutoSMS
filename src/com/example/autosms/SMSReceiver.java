package com.example.autosms;

import com.example.autosms.bean.SMSBean;
import com.example.autosms.db.DatabaseHandler;
import com.example.autosms.util.SPUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * 
 * 接收短信的广播
 */
public class SMSReceiver extends BroadcastReceiver {
	private static final String strRes = "android.provider.Telephony.SMS_RECEIVED";// 接受到短信的广播

	/* 当收到短信时，就会触发此方法 */
	public void onReceive(Context context, Intent intent) {

		if (strRes.equals(intent.getAction())) {// 判断是否是接受短信到来的广播
			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();// 获取到短信内容
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				SmsMessage[] msg = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					msg[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}
				for (SmsMessage curMsg : msg) {
					sb.append(curMsg.getDisplayMessageBody());// 短信信息
				}
				// Toast.makeText(context, "Got The Message:" + sb.toString(),
				// Toast.LENGTH_SHORT).show();
				SPUtils.put(context, "from", msg[0].getDisplayOriginatingAddress());
				SPUtils.put(context, "context", sb.toString());
				SPUtils.put(context, "message", "2");

				DatabaseHandler help = new DatabaseHandler(context);

				SMSBean bean = new SMSBean(0, msg[0].getDisplayOriginatingAddress(), sb.toString(), "");
				help.addSMS(bean);// 保存到数据

				String strNo = (String) SPUtils.get(context, "strNo", "10086");

				try {
					/**
					 * 收到短信后做短信转发
					 */
					SmsManager smsManager = SmsManager.getDefault();
					// 转发短信
					smsManager.sendTextMessage(strNo, null, sb.toString(), null, null);

					// 自动跳转
					String category = "android.intent.category.LAUNCHER";
					String action = "android.intent.action.MAIN";
					Intent myIntent = new Intent(context, MainActivity.class);// DateAcitivty为我的主程序界面
					myIntent.putExtra("from", "1");
					myIntent.setAction(action);
					myIntent.addCategory(category);
					myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(myIntent);
				} catch (Exception e) {
					Toast.makeText(context, "没有安装", Toast.LENGTH_LONG).show();
				}
			}
		}

	}

}
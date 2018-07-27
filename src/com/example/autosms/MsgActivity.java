package com.example.autosms;

import java.util.ArrayList;
import java.util.List;

import com.example.autosms.bean.SMSBean;
import com.example.autosms.db.DatabaseHandler;
import com.example.autosms.util.CommonAdapter;
import com.example.autosms.util.ViewHolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

/**
 * 短息中心
 */
public class MsgActivity extends Activity {

	private ListView list;// 链表显示
	private List<SMSBean> datas;// 短信的所有的数据
	private CommonAdapter<SMSBean> adapter;// 显示短信的适配器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.msg);
		list = (ListView) findViewById(R.id.list);
		datas = new ArrayList<SMSBean>();

	}

	// 显示所有收到的短信
	@Override
	protected void onResume() {
		super.onResume();

		// 拿到数据库操作对象
		DatabaseHandler h = new DatabaseHandler(MsgActivity.this);
		// 获取到数据
		List<SMSBean> temp = h.getALllMSG();
		if (temp != null) {
			datas = temp;
		}

		if (datas.size() > 0) {
			// 创建适配器
			adapter = new CommonAdapter<SMSBean>(MsgActivity.this, datas, R.layout.item_msg) {

				@Override
				public void convert(ViewHolder helper, SMSBean item) {
					helper.setText(R.id.phoneNum, "手机号码:" + item.getFrom());
					helper.setText(R.id.msg, "内容:" + item.getMessage() + "");
					helper.setText(R.id.time, "接收时间:" + item.getsTime() + "");
				}
			};
			// 添加适配器
			list.setAdapter(adapter);
		}

	}
}

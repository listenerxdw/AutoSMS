package com.example.autosms.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.autosms.bean.SMSBean;
import com.example.autosms.util.StrUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 短信数据库操作
 *
 */
public class DatabaseHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mydb.db";
	private static final String TABLE_NAME = "msg";
	private static final int VERSION = 1;
	private static final String KEY_PHONE = "phone";
	private static final String KEY_MSG = "message";
	private static final String KEY_TIME = "time";

	// 建表语句
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(id integer primary key autoincrement,"
			+ "phone text not null," + "message text not null," + "time text not null)";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
	}

	/**
	 * 添加短息
	 * 
	 * @param bean
	 */
	public void addSMS(SMSBean bean) {
		SQLiteDatabase db = this.getWritableDatabase();

		// 使用ContentValues添加数据
		ContentValues values = new ContentValues();
		values.put(KEY_PHONE, bean.getFrom() + "");
		values.put(KEY_MSG, bean.getMessage() + "");
		values.put(KEY_TIME, StrUtil.getStrByDate2(new Date()));
		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	// 查找所有SMSBean
	public List<SMSBean> getALllMSG() {

		List<SMSBean> List = new ArrayList<SMSBean>();

		String selectQuery = "select * from " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				SMSBean b = new SMSBean();
				b.setId(Integer.parseInt(cursor.getString(0)));
				b.setFrom(cursor.getString(1));
				b.setMessage(cursor.getString(2));
				b.setsTime(cursor.getString(3));
				List.add(b);
			} while (cursor.moveToNext());
		}
		return List;
	}

}
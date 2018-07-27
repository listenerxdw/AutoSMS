package com.example.autosms.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 公共类
 *
 */
public class ViewHolder {
	private final SparseArray<View> mViews;// 创建一个控件集合
	private int mPosition;// 获取位置
	private View mConvertView;// 拿到一个视图
	private Context context;

	private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.context = context;
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		// setTag
		mConvertView.setTag(this);// 设置当前标志
	}

	MyCallBack myListener;

	public void setActionListener(MyCallBack myListener1, int id) {
		this.myListener = myListener1;
		View view = getView(id);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				myListener.onClickListener();
			}
		});
	}

	/**
	 * 拿到一个ViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

	/**
	 * 设置视图是否显示
	 * 
	 * @param viewId
	 * @param v
	 */
	public void setViewVisible(int viewId, boolean v) {
		View view = getView(viewId);
		if (v) {
			view.setVisibility(View.VISIBLE);
		} else {
			view.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置监听方法
	 * 
	 * @param viewId
	 */
	public void setMylistener(int viewId) {
		View view = getView(viewId);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				myListener.onClickListener();
			}
		});
	}

	/**
	 * 设置带有颜色的规定的数据
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setTextWithColor(int viewId, String text, String status, int color) {
		TextView view = getView(viewId);
		view.setText(text);// 设置文字
		view.setTextColor(color);// 设置字体颜色
		return this;
	}

	// 设置带有背景的文本显示
	public ViewHolder setTextWithBG(int viewId, String text, int color) {
		TextView view = getView(viewId);
		view.setText(text);
		view.setBackgroundResource(color);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 *
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setRatingBarByLevel(int viewId, String level) {
		float f = Float.parseFloat(level);
		RatingBar view = getView(viewId);
		view.setRating(f);
		return this;
	}

	/**
	 * 设置控件是否显示
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setViewHind(int viewId, int VISIBLE) {
		View view = getView(viewId);
		view.setVisibility(VISIBLE);
		return this;
	}

}

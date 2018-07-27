package com.example.autosms.bean;

import java.io.Serializable;

/**
 * 
 * 短息内容
 */
public class SMSBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String from;// 短信来自
	private String message;// 短信内容
	private String sTime;// 收到短信的时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public SMSBean(int id, String from, String message, String sTime) {
		super();
		this.id = id;
		this.from = from;
		this.message = message;
		this.sTime = sTime;
	}

	public SMSBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}

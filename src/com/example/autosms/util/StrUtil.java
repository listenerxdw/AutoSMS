package com.example.autosms.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 **/
@SuppressLint("SimpleDateFormat")
public class StrUtil {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT4 = "yyyy-MM-dd";
	public static final String DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT1 = "yyyyMMddHHmmss";
	/**
	 * 时间格式，采�?24小时�?
	 */
	public static final String TIME_FORMAT = "HH:mm";
	/**
	 * 不带秒的时间日期格式
	 */
	public static final String DATE_FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";
	/**
	 * 不是时间的日期格�?
	 */
	public static final String DATE_FORMAT_NO_TIME = "yyyy-MM-dd";

	public int CheckUpdate() {
		int UpdateType = 0;
		return UpdateType;
	}

	@SuppressLint("SimpleDateFormat")
	public static String dateTranStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1);
		return dateFormat.format(date);
	}

	/**
	 * 获取小数的两位有效数�?
	 * 
	 * @param d
	 * @return
	 */
	public static double get2Double(Double d) {
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 获取小数的一位有效数�?
	 * 
	 * @param d
	 * @return
	 */
	public static double get1Double(Double d) {
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 判断输入的字符串参数是否为空
	 * 
	 * @return boolean 空则返回true,非空则flase
	 */
	public static boolean isEmpty(String input) {
		return null == input || 0 == input.length() || 0 == input.replaceAll("\\s", "").length() || input.equals("null")
				|| input.equals("[]");
	}

	/**
	 * 判断输入的字节数组是否为�?
	 * 
	 * @return boolean 空则返回true,非空则flase
	 */
	public static boolean isEmpty(byte[] bytes) {
		return null == bytes || 0 == bytes.length;
	}

	/**
	 * 金额元转�?
	 * 
	 * @see 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长�?
	 * @see 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方�?,否则计算出来的结果会令人大吃�?�?
	 * @param amount
	 *            金额的元进制字符�?
	 * @return String 金额的分进制字符�?
	 */
	public static String moneyYuanToFen(String amount) {
		if (isEmpty(amount)) {
			return amount;
		}
		// 传入的金额字符串代表的是�?个整�?
		if (-1 == amount.indexOf(".")) {
			return Integer.parseInt(amount) * 100 + "";
		}
		// 传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表�?
		int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
		// 取到小数点后面的字符�?
		String pointBehind = (amount.substring(amount.indexOf(".") + 1));
		// amount=12.3
		if (pointBehind.length() == 1) {
			return money_fen + Integer.parseInt(pointBehind) * 10 + "";
		}
		// 小数点后面的第一位字符串的整数表�?
		int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
		// 小数点后面的第二位字符串的整数表�?
		int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
		// amount==12.03,amount=12.00,amount=12.30
		if (pointString_1 == 0) {
			return money_fen + pointString_2 + "";
		} else {
			return money_fen + pointString_1 * 10 + pointString_2 + "";
		}
	}

	/**
	 * 字节数组转为字符�?
	 * 
	 * @see 该方法默认以ISO-8859-1转码
	 * @see 若想自己指定字符�?,可以使用<code>getString(byte[] data, String charset)</code>方法
	 */
	public static String getString(byte[] data) {
		String str = "";
		try {
			str = new String(data, "ISO-8859-1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * HTML字符转义
	 * 
	 * @see 对输入参数中的敏感字符进行过滤替�?,防止用户利用JavaScript等方式输入恶意代�?
	 * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
	 * @see HtmlUtils.htmlEscape(input); //from spring.jar
	 * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
	 * @see 尽管Spring和Apache都提供了字符转义的方�?,但Apache的StringEscapeUtils功能要更强大�?�?
	 * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
	 * @see 但二者在转义HTML字符�?,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
	 * @return String 过滤后的字符�?
	 */
	public static String htmlEscape(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;"); // IE暂不支持单引号的实体名称,而支持单引号的实体编�?,故单引号转义成实体编�?,其它字符转义成实体名�?
		input = input.replaceAll("\"", "&quot;"); // 双引号也�?要转义，�?以加�?个斜线对其进行转�?
		input = input.replaceAll("\n", "<br/>"); // 不能把\n的过滤放在前面，因为还要�?<�?>过滤，这样就会导�?<br/>失效�?
		return input;
	}

	// 返回对应的时间字符串
	@SuppressLint("SimpleDateFormat")
	public static String getStrByDate(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		str = sdf.format(date);
		return str;
	}

	// 返回对应的时间字符串
	@SuppressLint("SimpleDateFormat")
	public static String getStrByDate3(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NO_TIME);
		str = sdf.format(date);
		return str;
	}

	// 返回对应的时间字符串
	@SuppressLint("SimpleDateFormat")
	public static String getStrByDate2(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		str = sdf.format(date);
		return str;
	}

	// 返回对应的时间字符串
	@SuppressLint("SimpleDateFormat")
	public static String getStrByDate1(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT2);
		str = sdf.format(date);
		return str;
	}

	// 匹配输入数据类型
	public static boolean matchCheck(String ins, int type) {
		String pat = "";
		switch (type) {
		case 0: // /手机�?
			pat = "^1[3-8][0-9]{9}$";
			break;
		case 1:// /邮箱
			pat = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
			break;

		case 2: // /用户�?
			pat = "^[0-9a-zA-Z]{4,12}$";
			break;
		case 3: // /密码
			pat = "^[\\s\\S]{6,20}$";
			break;
		case 4: // /中文
			pat = "^[0-9a-z\u4e00-\u9fa5|admin]{2,15}$";
			break;
		case 5: // /非零正整�?
			pat = "^\\+?[1-9][0-9]*$";
			break;
		case 6: // /数字和字�?
			pat = "^[A-Za-z0-9]+$";
			break;
		case 7: // /1-9的数�?
			pat = "^[1-9]";
			break;
		case 8: // /身份�?
			pat = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
			break;
		case 9: // /名字
			pat = "^([A-Za-z]|[\u4E00-\u9FA5])+$";
			break;
		case 10: // /时间 时：分：�?
			pat = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
			break;
		}
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(ins);
		return m.matches();
	}

	/*
	 * public static Boolean CheckNetwork(Context cont, String tips) { try{
	 * ConnectivityManager
	 * cwjManager=(ConnectivityManager)cont.getSystemService(
	 * cont.CONNECTIVITY_SERVICE); NetworkInfo info =
	 * cwjManager.getActiveNetworkInfo(); if (info == null &&
	 * !info.isAvailable()){ if(tips!=null&&!tips.equals("")) {
	 * Toast.makeText(cont,tips,Toast.LENGTH_LONG).show(); } return false; } }
	 * catch(Exception e) {
	 * 
	 * Toast.makeText(cont,tips,Toast.LENGTH_LONG).show(); return false; }
	 * 
	 * return true; }
	 */
	public static String md5(String input) {
		String res = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(input.getBytes());
			byte[] md5 = algorithm.digest();
			String tmp = "";
			for (int i = 0; i < md5.length; i++) {
				tmp = (Integer.toHexString(0xFF & md5[i]));
				if (tmp.length() == 1) {
					res += "0" + tmp;
				} else {
					res += tmp;
				}
			}
		} catch (NoSuchAlgorithmException ex) {
		}
		return res;
	}

	/**
	 * 将日期转换成字符串格�?
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateTran2Str(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(StrUtil.DATE_FORMAT_NO_SECOND);
		return dateFormat.format(date);
	}

	/**
	 * 将日期转换成字符串格�?
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String BillRandom(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(StrUtil.DATE_FORMAT1);
		return dateFormat.format(date);
	}

	/**
	 * 将日期转换成字符串格�?
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateTran2Str(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 将日期转换成字符串格�?
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateTran2Str(Object date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 将日期转换成字符串格�?
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateTran2Str(Object date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NO_SECOND);
		return dateFormat.format(date);
	}

	/**
	 * 带秒的时间格�?
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateTranStr2exc(Object date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT2);
		return dateFormat.format(date);
	}

	/**
	 * 将日期型字符串转换为日期
	 * 
	 * @param dateStr
	 *            �?定要是日期转过来的日期型字符串，否则会有ParseException
	 * @return null表示ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date strTran2Date(String dateStr) {
		try {
			return new SimpleDateFormat(StrUtil.DATE_FORMAT_NO_SECOND).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期型字符串转换为日期
	 * 
	 * @param dateStr
	 *            �?定要是日期转过来的日期型字符串，否则会有ParseException
	 * @param pattern指定的时间格
	 *            �?
	 * @return null表示ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date strTran2Date(String dateStr, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * float型数据转换成String
	 * 
	 * @param f
	 * @return
	 */
	public static String floatTran2Str(float f) {
		return String.valueOf(f);
	}

	/**
	 * 将时间转换成long型时间戳
	 * 
	 * @param timeStr
	 *            时间的字符串表示，格式为：HH:mm
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Long strTran2Time(String timeStr) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
		Date time = null;
		try {
			if (timeStr.length() > 10) {
				timeStr = timeStr.substring(10);
			}
			time = timeFormat.parse(timeStr);
			return time.getTime();
		} catch (ParseException e) {
			System.out.println(new Exception("日期格式非法�?").getMessage());
		}
		return null;
	}

	/**
	 * 将数据库的时间转换成字符�?
	 * 
	 * @param t时间
	 * @return
	 */
	public static String timeTran2Str(Object t) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
		String timeStr = null;
		timeStr = timeFormat.format(t);
		return timeStr;
	}

	/**
	 * 将时间戳转换成字符串
	 * 
	 * @param timestampString
	 * @return
	 */
	public static String timeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(DATE_FORMAT2).format(new java.util.Date(timestamp));
		return date;
	}

	/**
	 * 将时间戳转换成字符串
	 * 
	 * @param timestampString
	 * @return
	 */
	public static String timeStamp2Date1(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(DATE_FORMAT4).format(new java.util.Date(timestamp));
		return date;
	}

	public static String strTranBirth(String day) {
		if (day.length() == 8) {
			String y = day.substring(0, 4);
			String m = day.substring(4, 6);
			String d = day.substring(6, 8);
			return y + "-" + m + "-" + d;
		}
		return day;
	}

	/**
	 * 汉字转拼音缩�?
	 * 
	 * @param str
	 *            要转换的汉字字符�?
	 * @return String 拼音缩写
	 */
	public static String getPYString(String str) {
		String tempStr = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 33 && c <= 126) {// 字母和符号原样保�?
				tempStr += String.valueOf(c);
			} else {// 累加拼音声母
				tempStr += getPYChar(String.valueOf(c));
			}
		}

		if (tempStr.length() > 1) {
			tempStr = tempStr.substring(0, 1);
		}

		return tempStr;
	}

	/**
	 * 取单个字符的拼音声母
	 * 
	 * @param c
	 *            //要转换的单个汉字
	 * @return String 拼音声母
	 */

	public static String getPYChar(String c) {
		byte[] array = new byte[2];
		array = String.valueOf(c).getBytes();
		int i = (short) (array[0] - '\0' + 256) * 256 + ((short) (array[1] - '\0' + 256));
		if (i < 0xB0A1)
			return "*";
		if (i < 0xB0C5)
			return "a";
		if (i < 0xB2C1)
			return "b";
		if (i < 0xB4EE)
			return "c";
		if (i < 0xB6EA)
			return "d";
		if (i < 0xB7A2)
			return "e";
		if (i < 0xB8C1)
			return "f";
		if (i < 0xB9FE)
			return "g";
		if (i < 0xBBF7)
			return "h";
		if (i < 0xBFA6)
			return "j";
		if (i < 0xC0AC)
			return "k";
		if (i < 0xC2E8)
			return "l";
		if (i < 0xC4C3)
			return "m";
		if (i < 0xC5B6)
			return "n";
		if (i < 0xC5BE)
			return "o";
		if (i < 0xC6DA)
			return "p";
		if (i < 0xC8BB)
			return "q";
		if (i < 0xC8F6)
			return "r";
		if (i < 0xCBFA)
			return "s";
		if (i < 0xCDDA)
			return "t";
		if (i < 0xCEF4)
			return "w";
		if (i < 0xD1B9)
			return "x";
		if (i < 0xD4D1)
			return "y";
		if (i < 0xD7FA)
			return "z";

		return "*";
	}

	/**
	 * 或者设备信息
	 * 
	 * @param context
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager mTm = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
		String imei = mTm.getDeviceId();
		// String imsi = mTm.getSubscriberId();
		// String mtype = android.os.Build.MODEL; // 手机型号
		// String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
		return imei;
	}
}

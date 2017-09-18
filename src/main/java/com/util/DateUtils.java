package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils; 

/**
 * 
 * @author <a href="mailto:wangxuhui@ssreader.cn">wangxuhui</a>
 * @version 2012-12-28
 */
public class DateUtils {

	static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	static final SimpleDateFormat yyyyMMddformatter_zh = new SimpleDateFormat("yyyy年MM月dd日");

	static final SimpleDateFormat MMdd = new SimpleDateFormat("MMdd");

	static final SimpleDateFormat Mdd = new SimpleDateFormat("M-dd");

	public static String getTimeFromLong(Long time) {
		Date date = new Date(time);
		return formatter.format(date);
	}

	public static String getTimeFromLongDetail(Long time) {
		Date date = new Date(time);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获取当前系统时间 Long
	 * 
	 * @return
	 */
	public static Long getCurrentDateTimeLong() {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getCurrentDateTime());
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static String MMdd(long time) {
		Date date = new Date(time);
		return MMdd.format(date);
	}

	public static String Mdd(long time) {
		Date date = new Date(time);
		return Mdd.format(date);
	}

	public static int getWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	// 返回2014-3-26
	public static String YYMMDD(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(time);
			return format.format(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

	}

	// 返回2014-3-26
	public static String YYMMDD(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.format(date);
		}
		catch (Exception e) {
			return "";
		}

	}

	// 返回2015-12-24
	public static String YYMMDD(long time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.format(new Date(time));
		}
		catch (Exception e) {
			return "";
		}

	}

	// 返回2016-01-05
	public static String YYMMDD_ZH(Date date) {
		try {
			return yyyyMMddformatter_zh.format(date);
		}
		catch (Exception e) {
			return "";
		}
	}

	/**
	 * 返回值 格式 10月2日 英文格式 Oct 2
	 */
	public static String engFormat(Locale locale) {
		if (locale == null) {
			locale = Locale.getDefault();
		}
		if (locale.getLanguage().equals("en")) {
			Date date = new Date();
			String day = String.format("%td", date);
			String month = String.format(locale, "%tb", date);
			return month + " " + day;
		}
		else {
			Calendar calendar = Calendar.getInstance(locale);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			return month + "月" + day + "日";

		}
	}

	public static String engFormat(long inserttime, Locale locale) {
		if (locale == null) {
			locale = Locale.getDefault();
		}
		Date date = new Date();
		date.setTime(inserttime);
		if (locale.getLanguage().equals("en")) {

			String day = String.format("%td", date);
			String month = String.format(locale, "%tb", date);
			return month + " " + day;
		}
		else {
			Calendar calendar = Calendar.getInstance(locale);
			calendar.setTime(date);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			return month + "月" + day + "日";
		}
	}

	/**
	 * data类型的日期 设置国际化
	 */
	public static String engDateFormat(Date date, Locale locale) {
		if (locale == null || date == null) {
			locale = Locale.getDefault();
		}
		if (locale.getLanguage().equals("en")) {

			String day = String.format("%td", date);
			String month = String.format(locale, "%tb", date);
			return month + " " + day;
		}
		else {
			String day = String.format("%td", date);
			String month = new SimpleDateFormat("MM").format(date);
			return month + "月" + day + "日";
		}
	}

	public static long dateToLong(String source, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(source);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	// 获取当前日期
	public static String getDate() {
		Date date = new Date();
		return formatter.format(date);
	}

	public static String getDate(Date date, String formatStr) {
		if (StringUtils.isBlank(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm:ss";// yyyy-MM-dd HH:mm:ss
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return date == null ? "" : format.format(date);
	}

	// 计算当前日期与指定日期的时间差
	public static long getQuot(String curDate, String specifyDate) {
		long quot = 0;
		try {
			Date date1 = formatter.parse(curDate);
			Date date2 = formatter.parse(specifyDate);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	// 计算当前日期与指定日期的时间差
	public static long getQuot(Date date1, Date date2) {
		long quot = 0;
		try {
			// Date date1 = formatter.parse(curDate);
			// Date date2 = formatter.parse(specifyDate);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static String dateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 实现日期增加天数方法
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static String addDay(Date date, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天

			return sdf.format(cd.getTime());

		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 两个日期的时间差, 毫秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differ(long dateTime1, long dateTime2) {
		return dateTime1 - dateTime2;
	}

	public static Date getDateTime(String dtime, String formatStr) {
		try {
			DateFormat format1 = new SimpleDateFormat(formatStr);
			return format1.parse(dtime);
		}
		catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 两个日期的时间差, 毫秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differ(Date date1, Date date2) {
		return differ(date1.getTime(), date2.getTime());
	}

	/**
	 * 两个日期的时间差, 分
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differMinute(long date1, long date2) {
		return differ(date1, date2) / (1000 * 60);
	}

	/**
	 * 两个日期的时间差, 分
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differMinute(Date date1, Date date2) {
		return differMinute(date1.getTime(), date2.getTime());
	}

	/**
	 * 与当前时间比较，判断是否开始，开始返回true
	 */
	public static long dateDistance(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long minute = 0;
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long l = d2.getTime() - d1.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			// long s=(l/1000-day*24*60*60-hour*60*60-min*60);
			minute = day * 24 * 60 + hour * 60 + min;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return minute;
	}

	/**
	 * Date格式转换为String("yyyy-MM-dd HH:mm:ss")
	 */
	public static String dateToString3(Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String dateToString(Date date, String formatStr) {
		if (date == null)
			return "";
		return new SimpleDateFormat(formatStr).format(date);
	}

	/**
	 * 与当前时间比较，判断是否过期，过期返回true
	 */
	public static boolean equalsDate(Date date) {
		boolean bl = false;
		if (date != null) {
			try {
				if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateToString3(new Date())).after(date)) {// 当前时间大于给定时间-返回true
					bl = true;
					if (new Date().after(date) || new Date().equals(date)) {
						bl = true;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bl;
	}

	/**
	 * 与当时时间比较，是否在参数时间范围内
	 * @param startDate 开始时间
	 * @param endStart 结束时间
	 * @return
	 */
	public static boolean eqDate(String startDate, String endDate) {
		boolean bl = false;
		try {
			if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
				long l_startDate = dateToLong(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				long l_endDate = dateToLong(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				long l_currDate = getCurrentDateTimeLong();
				if (l_currDate >= l_startDate && l_currDate <= l_endDate) {
					bl = true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}

	/**
	 * 与当前时间比较，相差多少秒
	 */
	public static long dateDistanceSecond(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long second = 0;
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long l = d2.getTime() - d1.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			second = day * 24 * 60 * 60 + hour * 60 * 60 + min * 60 + s;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return second;
	}

	/**
	 * Date格式转换为String("yyyy-MM-dd HH:mm:ss")
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	/**
	 * 学年规则 如果是2013 上半年，学年为2012 。2013下半年，学年为 2013
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		if (m >= 9 || m == 1) {
			return y;
		}
		else {
			return y - 1;
		}
	}

	/**
	 * 根据当前日期判断是哪一学年
	 */
	public static int getTerm() {
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;
		if (m >= 9 || m == 1) {
			return 1;
		}
		else {
			return 2;
		}
	}

	/**
	 * Date格式转换为String("MM-dd HH:mm")
	 */
	public static String dateToString2(Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	public String dateToStringFormat(Date date, String format) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static void main(String[] args) {
		System.out.println(dateToStr(new Date(), "yyyy\\MM\\dd"));
	}
}

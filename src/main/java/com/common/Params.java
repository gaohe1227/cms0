/**********************************************************************************
 * 
 * Description         :公用参数设置
 * Version             :v1.0
 * JDK Version         :1.6.10
 * Sr.       Date		Modified By		Why and What is Modified
 * 1.     2009.06.11	Jie Zhou		Initial Release 
 * 
 **********************************************************************************/
package com.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

/**
 * 
 * @see java.text.Format
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * 
 */
public class Params {

	/**
	 * 获得编码
	 * 
	 * @return
	 */
	public static String getCode() {

		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
		String code = format.format(date);
		return code;
	}

	/**
	 * 获得系统时间
	 * 
	 * @return
	 */
	public static String systemDateTime() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		String dataTime = format.format(date);
		return dataTime;
	}

	/**
	 * 获得系统日期
	 * 
	 * @return
	 */
	public static String systemDate() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String dataTime = format.format(date);
		return dataTime;
	}

	/**
	 * 获得系统年份
	 * 
	 * @return
	 */
	public static String systemYear() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyy", Locale.CHINA);
		String dataTime = format.format(date);
		return dataTime;
	}

	/**
	 * 获得系统时间
	 * 
	 * @return
	 */
	public static String systemDate(String pattern) {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Date date = new Date();
		Format format = new SimpleDateFormat(pattern);
		String dataTime = format.format(date);
		return dataTime;
	}

	public static String systemDateForYMD() {
		String year = systemDate("yyyy");
		String month = systemDate("M");
		String day = systemDate("d");
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 时间格式转换 如：2012-09-09 —— 2012年09月09日
	 * */
	public static String zhDate(Object obj) {
		String str = obj.toString();
		int count = Params.split(str, "-").length;
		String rq = "";
		if (str != null && str != "" && count != 1) {
			rq = Params.split(str, "-")[0] + "年" + Params.split(str, "-")[1]
					+ "月" + Params.split(str, "-")[2] + "日";
		} else if (count == 1) {
			return str;
		}
		return rq;
	}

	/**
	 * 时间格式转换 如：2012-09-09-09:09 —— 2012年09月09日09时09分
	 * */
	public static String zhsfDate(Object obj) {
		String str = obj.toString();
		int count = Params.split(str, "-").length;
		String rq = "";
		if (str != null && str != "" && count != 1) {
			rq = Params.split(str, "-")[0] + "年" + Params.split(str, "-")[1]
					+ "月" + Params.split(Params.split(str, " ")[0], "-")[2]
					+ "日" + Params.split(Params.split(str, " ")[1], ":")[0]
					+ "时" + Params.split(Params.split(str, " ")[1], ":")[1]
					+ "分";
		} else if (count == 1) {
			return str;
		}
		return rq;

	}

	/**
	 * 时间格式转换 如：09:09 —— 09时09分
	 * */
	public static String sfDate(Object obj) {

		String str = obj.toString();
		int count = Params.split(str, "-").length;
		String rq = "";
		if (str != null && str != "" && count != 1) {
			rq = Params.split(str, ":")[0] + "时" + Params.split(str, ":")[1]
					+ "分";
		} else if (count == 1) {
			return str;
		}
		return rq;

	}

	/**
	 * 获得本月的星期
	 * 
	 * @return
	 */

	public static String systemWeekDay() {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int i = c.get(Calendar.DAY_OF_WEEK) - 1;
		String day = "";
		switch (i) {
		case 0:
			day = "星期日";
			break;
		case 1:
			day = "星期一";
			break;
		case 2:
			day = "星期二";
			break;
		case 3:
			day = "星期三";
			break;
		case 4:
			day = "星期四";
			break;
		case 5:
			day = "星期五";
			break;
		case 6:
			day = "星期六";
			break;
		default:
			day = "";
		}

		return day;
	}

	/**
	 * 将字符串转换成数组
	 */

	public static String[] split(String args, String str) {
		if (args == null || "".equals(args)) {
			return new String[0];
		}
		List list = new ArrayList();
		if (args.indexOf(str) == -1) {
			String[] s = new String[1];
			s[0] = args;
			return s;
		}
		if (args.substring(args.length() - 1).equals(str)) {
			args = args.substring(0, args.length() - 1);
		}
		while (true) {
			if (args.indexOf(str) != -1) {
				list.add(args.substring(0, args.indexOf(str)));
				args = args.substring(args.indexOf(str) + 1);
			} else {
				list.add(args);
				String[] s = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					s[i] = (String) list.get(i);
				}
				return s;
			}
		}
	}

	/**
	 *获得访问IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	public static String addStr(int num, String str) {
		String newStr = str;
		for (int i = 0; i < num; i++) {
			newStr += str;
		}
		return newStr;

	}

	/**
	 * 按照arg格式替换成arg1
	 * 
	 * @param arg
	 * @param args1
	 * @param str
	 *            分割符
	 * @return
	 */
	public static String replaceFormat(String arg, String arg1, String str) {
		String[] args = Params.split(arg, str);
		String newStr = "";
		for (int i = 0; i < args.length; i++) {
			newStr += Params.addStr(i, arg1) + str;
		}
		if ("".equals(newStr)) {
			return arg;
		} else {
			return newStr;
		}

	}

	/**
	 * 特殊符号处理
	 * 
	 * @param String
	 * @return String
	 */
	public static String specialSymbols(String str) {

		if (str.indexOf("<", 1) > 0) {

			str = str.replaceAll("<", "&lt;");

		} else if (str.indexOf(">", 1) > 0) {

			str = str.replaceAll(">", "&gt;");

		} else if (str.indexOf("&", 1) > 0) {

			str = str.replaceAll("&", "&amp;");

		} else if (str.indexOf("'", 1) > 0) {

			str = str.replaceAll("'", "&apos;");

		} else if (str.indexOf("\"", 1) > 0) {

			str = str.replaceAll("\"", "&quot;");

		}

		return str;
	}

	public static String trim(String arg) {
		String str = "";
		for (int i = 0; i < arg.length(); i++) {
			if (arg.charAt(i) != ' ') {
				str += String.valueOf(arg.charAt(i));
			}
		}

		return str;
	}

	/**
	 * 特殊符号处理
	 * 
	 * @param 数据库BLOB
	 *            存的二进制流文件
	 * @return String
	 */

	public static String ioToBase64(InputStream inBlob) throws IOException {
		String strBase64 = null;
		try {

			InputStream in = inBlob;
			// in.available()返回文件的字节长度
			byte[] bytes = new byte[in.available()];
			// 将文件中的内容读入到数组中
			in.read(bytes);
			strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
			in.close();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return strBase64;
	}

	/**
	 * 折算
	 */
	public static String conver(double base, double scale) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format((base / scale) * ((double) 100)) + "%";
	}

	public static String conver(String str) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(Double.parseDouble(str)) + "%";
	}

	/**
	 * 字典表查询
	 * 
	 * 
	 * public static String zidian(String zd){
	 * 
	 * //return aa; }
	 */

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 *            --文件完整路径
	 * @param response
	 *            --HttpServletResponse对象
	 */
	public static void downloadFile(String filePath, String fileName,
			javax.servlet.http.HttpServletResponse response) {

		// System.out.println(src_fname);System.out.println(fileName);
		try {
			response.setCharacterEncoding("UTF-8");
			boolean isOnLine = false;

			BufferedInputStream buffer = null;
			OutputStream out = null;
			try {

				File f = new File(filePath);
				// 检查该文件是否存在
				if (!f.exists()) {
					response.sendError(404, "File not found!");
				}
				buffer = new BufferedInputStream(new FileInputStream(f));
				byte[] buf = new byte[1024];
				int len = 0;

				response.reset(); // 非常重要
				if (isOnLine) { // 在线打开方式
					System.out.println("在线打开方式");
					URL u = new URL("file:///" + filePath);
					response
							.setContentType(u.openConnection().getContentType());
					response
							.setHeader("Content-Disposition",
									"inline; filename="
											+ (f.getName()).getBytes("gbk"));
					// 文件名应该编码成UTF-8
				} else { // 纯下载方式
					System.out.println("纯下载");
					// response.setContentType("application/x-msdownload");
					response
							.setContentType("application/vnd.ms-excel;charset=GBK");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + fileName);

				}
				out = response.getOutputStream();
				while ((len = buffer.read(buf)) > 0) {
					out.write(buf, 0, len);
					out.flush();
				}

			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				try {
					buffer.close();
					out.close();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("文件下载完成");
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!isChinese(c)) {
					count = count + 1;
					// System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 去掉html元素, *
	 * 
	 * @param input
	 * 
	 * @param length
	 * 
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	public static void getSuccess(HttpServletResponse response) {
		Writer out1 = null;
		try {
			out1 = response.getWriter();
			out1.write(String.valueOf("success"));
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getFail(HttpServletResponse response) {
		Writer out1 = null;
		try {
			out1 = response.getWriter();
			out1.write(String.valueOf("fail"));
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getUserself(HttpServletResponse response, String userself) {
		Writer out1 = null;
		try {
			out1 = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			out1.write(String.valueOf(userself));
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File f) throws Exception {// 
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	/**
	 * 取得文件夹大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception// 
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

}
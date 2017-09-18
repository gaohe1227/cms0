package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

/**
 * @author guoqiang 2013-11-14
 * 
 */
public class MD5 {

	static String key = "F0hZ~/@-4]Pv";

	public static String calcMD5(String str) {
		return MessageEncrypt.getInstance("md5").encode(str);
	}

	public static String calcMD5ForCourse(String str) {
		return MessageEncrypt.getInstance("md5").encode(str + key);
	}

	/**
	 * 验证握手串
	 * @param str
	 * @param enc
	 * @return2015年10月26日
	 */
	public static boolean isEnc(String str, String enc) {
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(enc)) {
			return false;
		}
		else {
			String temp = MD5.calcMD5(str + key);
			return enc.equalsIgnoreCase(temp);
		}
	}

	/**
	 * 返回经MD5加密后的密文
	 */
	public static String getSecurityKey(String str) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("utf-8"));
			byte b[] = md.digest();

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
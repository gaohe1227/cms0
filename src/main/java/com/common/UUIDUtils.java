package com.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * UUID类实现GUID生成功能
 * 
 * @Project:综合考评系统
 * @ClassName: UUID.java
 * @Company: JIT Northeast R & D
 * @Description: TODO
 * @author litie_fan
 * @date 2012-8-17
 */
public final class UUIDUtils {

	private static int cnt = 0;

	private static long lastTime = System.currentTimeMillis();

	/**
	 * MAC地址与随机数的组合，作为生成UUID的种子
	 */
	private static String MAC = String.valueOf(System.currentTimeMillis());

	static {
		try {
			MAC = InetAddress.getLocalHost().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				MAC = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		} finally {
			MAC = MAC.replaceAll("_", "");
			MAC = MAC.replaceAll(" ", "");
			MAC = MAC.replaceAll("-", "");
			MAC = MAC.replaceAll("\\.", "");
			MAC += System.currentTimeMillis();
		}
	}

	/**
	 * 获取对象唯一标识，简单算法，使用IP地址加上时间
	 * 
	 * @return 随机字符串，不能保证全局唯一
	 */
	public static synchronized String getSimpleUID() {
		long l = System.currentTimeMillis();
		if (l == lastTime) {
			cnt++;
		} else {
			cnt = 0;
		}
		lastTime = l;
		String s = MAC + cnt + l;

		return s;
	}

	/**
	 * 生成UUID
	 * 
	 * @return 生成的UUID，如C2FEEEACCFCD11D18B0500600806D9B6
	 * @throws PlatformException
	 *             生成UUID过程出错时抛出异常，异常信息中包括出错原因
	 */
	public static synchronized String getUID() {
		try {
			RandomGUID uid = new RandomGUID();
			String uidStr = uid.valueAfterMD5;
			return uidStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 将缺省的UUID格式转换为另一种显示格式，如C2FEEEACCFCD11D18B0500600806D9B6转换为C2FEEEAC-CFCD-
	 * 11D1-8B05-00600806D9B6
	 * 
	 * @param uuid
	 *            原UUID
	 * @param toUpper
	 *            是否转换为大写
	 * @return 转换后的UUID
	 */
	public static String format(final String uuid, final boolean toUpper) {
		String raw = uuid;
		if (toUpper) {
			raw = uuid.toUpperCase();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));

		return sb.toString();
	}

}
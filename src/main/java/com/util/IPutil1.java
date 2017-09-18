package com.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

 

public class IPutil1 {

	private static IPutil1 instance = new IPutil1();

	private IPutil1() {
	}

	public static IPutil1 getInstance() {
		return instance;
	}

	public static String getClientIp(HttpServletRequest request) {

		if (request == null) {
			return "127.0.0.1";
		}
		try {
			 
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			Pattern pattern = Pattern
					.compile("^\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b$");
			Matcher matcher = pattern.matcher(ip);

			if (matcher.find()) {
				return ip;
			}
			if (ip != null) {
				if (ip.contains(",")) {
					String[] ips = ip.split(",");
					for (int i = 0; i < ips.length; i++) {
						String tip = ips[i];
						if (tip != null) {
							tip = tip.trim();
							if (!isInnerIP(tip)) {
								return tip;
							}
						}
					}
					return ips[0].trim();
				}
				return "127.0.0.1";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "127.0.0.1";
	}

	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		/**
		 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
		 * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
		 **/
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
		return isInnerIp;
	}

	private static long getIpNum(String ipAddress) {
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);

		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}

	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}

	public String getTrueRemoteAddr(HttpServletRequest request) {
		try {
			 
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
		catch (Exception e) {
			return null;
		}
	}

	public boolean isIE(HttpServletRequest request) {
	 
		if (request!= null) {
			String agentStr = request.getHeader("user-agent");
			if (agentStr == null || StringUtils.containsIgnoreCase(agentStr, "Trident/7.0")) {
				return false;
			}
			return StringUtils.containsIgnoreCase(agentStr, "MSIE") || StringUtils.containsIgnoreCase(agentStr, "Trident");
		}
		return false;
	}

	public static String getServerIp() {
		String ip;
		try {
			InetAddress add = null;
			add = InetAddress.getLocalHost();
			ip = add.getHostAddress();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
			ip = "127.0.0.1";
		}
		return ip;
	}

}

package com.util;

 

import javax.servlet.http.HttpServletRequest;
/**
 * 处理ip工具类
 * Description:
 * @author:高鹤
 * @date:2017年8月25日
 */
public class IpUtils {
	/**
	 * 获取真实ip
	 * @Title: getClientIp
	 * @Description: TODO
	 * @param @param request
	 * @return void
	 * @throws
	 */
	public static String getClientIp(HttpServletRequest request){
		String ip= request.getRemoteAddr();
		System.out.println(ip+"----------------"+request.getHeader("x-forwarded-for"));
	 
		if(request.getHeader("x-forwarded-for")==null){
			if(ip.equals("0:0:0:0:0:0:0:1")){
				return "127.0.0.1";
			}
		}
		return ip;
	}

}

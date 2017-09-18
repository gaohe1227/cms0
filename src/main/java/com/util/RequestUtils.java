package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.files.controller.FilesController;

/**
 * request工具
 * @author <a href="mailto:xieliqi@chaoxing.com">xieliqi</a>
 * @version 2014-1-16
 */
public class RequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	/**
	 * 
	 * 获取get url参数
	 * @param inv
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		return getRequestUrl(request, null, false);
	}

	/**
	 * 
	 * 获取get url参数
	 * @param inv
	 * @return
	 */
	public static String getRequestUrlALL(HttpServletRequest request) {
		return getRequestUrl(request, null, true);
	}

	/**
	 * 
	 * 获取get url参数
	 * @param inv
	 * @param filter 过滤的参数
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request, String[] filter) {
		return getRequestUrl(request, filter, false);
	}

	/**
	 * 
	 * 获取get url参数
	 * @param inv
	 * @param filter 过滤的参数
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request, String[] filter, boolean isALL) {
		if (!isALL) {
			if (filter == null || filter.length == 0) {
				filter = new String[] { "currentPage", "pageSize" };
			}
		}
		else {
			filter = new String[] {};
		}
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			boolean iscontinue = false;
			for (int i = 0; i < filter.length; i++) {
				String str = filter[i];
				if (key.trim().equalsIgnoreCase(str.trim())) {
					iscontinue = true;
					break;
				}
			}
			if (iscontinue)
				continue;
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				try {
					queryString += key + "=" + URLEncoder.encode(value, "utf-8") + "&";
				}
				catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (!StringUtils.isBlank(queryString)) {
			// 去掉最后一个空格
			queryString = queryString.substring(0, queryString.length() - 1);
		}
		return queryString;
	}

	 

	/**
	 * 获取referer 的请求path
	 * @return
	 */
	public static String getRefererPath(HttpServletRequest request) {
		String referer = request.getHeader("referer");

		if (StringUtils.isBlank(referer)) {
			return referer;
		}
		String refererPath = null;
		try {
			refererPath = referer.substring(referer.indexOf(".com") + 4);
			refererPath = refererPath.split("[?]")[0];
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return refererPath;
	}

}

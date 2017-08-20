package com.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HTTPRequestUtil {
	/**
	 * 从request中获取请求字段到map，字段有多个值则在map表现为数组 将移除filterName中的字段
	 */
	public static Map getParamsMap(HttpServletRequest request,
			String[] filterNames) {
		Map map = new HashMap();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String[] value = request.getParameterValues(name);
			if (null != value && value.length > 0) {
				if (value.length > 1) {
					for (int i = 0; i < value.length; i++) {
						value[i] = paramsURLDecode(value[i]);
					}
					map.put(name, value);
				} else {
					map.put(name, paramsURLDecode(value[0]));
				}
			}
		}

		if (null != filterNames) {
			for (String name : filterNames) {
				map.remove(name);
			}
		}
		return map;
	}

	/**
	 * 从request中获取请求字段到map，字段有多个值则在map表现为数组 将移除functionid, method两个字段
	 */
	public static Map getParamsMap(HttpServletRequest request) {
		String[] filterNames = { "functionid", "method" };
		return getParamsMap(request, filterNames);
	}

	/**
	 * 从request中获取请求字段
	 */
	public static <T> T getBean(HttpServletRequest request, Class<T> beanClass) {
		T t = null;
		try {
			t = beanClass.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				if ("class".equals(pd.getName())) {
					continue;
				} else {
					String feileName = pd.getName();
					String[] valuse = request.getParameterValues(feileName);
					if (null != valuse && valuse.length > 0) {
						Method method = pd.getWriteMethod();
						if(valuse.length == 1 ){
							method.invoke(t, paramsURLDecode(valuse[0]));
						}else {
							for (int i = 0; i < valuse.length; i++) {
								valuse[i] = paramsURLDecode(valuse[i]);
							}
							method.invoke(t, valuse);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		return t;
	}

	public static String paramsURLDecode(String param) {
		try {
			return null == param || "".equals(param) ? param : URLDecoder
					.decode(param, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}


}

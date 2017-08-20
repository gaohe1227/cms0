package com.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
	private FilterConfig filterConfig;

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// 如果过滤滤镜为*.jsp，则不能过滤action,.do这些路径 ，如果过滤路径为/*, 那么js jpg dwr之类的都会被过滤
		// ,那么会跳到超时页面
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		String path = req.getContextPath() + "/";
		req.setCharacterEncoding("UTF-8");
		// System.out.println(req.getRequestURL());
		response.setCharacterEncoding("UTF-8");
		if (session.getAttribute("loginUser") != null) {// 如果session没超时，请求通过
			chain.doFilter(request, response);
		} else {
			if (url.indexOf("login.do") != -1 // 如果session超时，而请求是登录页面或其他.do，请求通过
					|| url.indexOf("custommenuContent.jsp") != -1
					|| url.indexOf("index.do") != -1
					|| url.indexOf("weixinneirong.jsp") != -1
					|| url.indexOf("/chaxun/") != -1
					|| url.indexOf("systemOut.do") != -1
					|| url.indexOf("/system/") != -1
					|| url.indexOf("system/login.jsp") != -1
					|| url.indexOf("/main/") != -1
					|| url.indexOf("main/login.jsp") != -1
					|| url.indexOf("userControl") != -1 // 确保在没有session对象的情况下，首页登录的userControl模块的.do请求通过验证
					|| url.equals(path)) {
				chain.doFilter(request, response);
			} else { // 如果session超时并且访问的是内部url ， 跳转到session超时页面
				request.getRequestDispatcher("/jsp/system/sessionError.jsp")
						.forward(request, response);
			}
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}
}

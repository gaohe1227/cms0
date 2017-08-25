package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.base.model.User;

/**
 * Servlet Filter implementation class SessuionFilter
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		  HttpServletResponse response = (HttpServletResponse) servletResponse; 
		  String uri = httpServletRequest.getRequestURI(); 
		//  System.out.println("uri====>"+uri);
			 if(!uri.startsWith("/login")&&!uri.startsWith("/jquery")&&!uri.startsWith("/css")&&!uri.startsWith("/druid/")){ 
				  User loginuser =   (User) httpServletRequest.getSession().getAttribute("loginuser");
		          if(loginuser==null){ 
		        	 // System.out.println("uri====>"+uri);
		        	   response.sendRedirect("/login");
		        	   return;
		          }
		       
			 }
		
			 chain.doFilter(request, response);
			
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

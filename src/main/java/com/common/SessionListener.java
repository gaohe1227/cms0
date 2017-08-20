package com.common;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	private static Hashtable hUserName = new Hashtable();

	public void sessionCreated(HttpSessionEvent se) {

	}

	public void sessionDestroyed(HttpSessionEvent se) {
		Set set = hUserName.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			HttpSession session = (HttpSession) hUserName.get(key);
			if (session.getId().equals(se.getSession().getId())) {
				hUserName.remove(key);
			}
		}
	}

	/**
	 * 独享模式判断用户是否存在
	 * 
	 * @param session
	 * @param userName
	 * @return
	 */
	public synchronized static boolean isLogin(HttpSession session,
			String userName) {
		boolean flag = false;
		if (hUserName.containsKey(userName)) {
			flag = true;
		} else {
			hUserName.put(userName, session);
		}
		return flag;
	}

	/**
	 * 抢占模式判断用户是否已经登陆
	 */
	public synchronized static void isLogined(HttpSession session,
			String userName) {

		if (hUserName.containsKey(userName)) {
			HttpSession vsession = (HttpSession) hUserName.get(userName);
			try {
				if (vsession.getAttribute("loginUser") != null) {
					vsession.setAttribute("loginUser", null);
				}
				vsession.invalidate();
				hUserName.remove(userName);
				hUserName.put(userName, session);
			} catch (Exception ex) {

			}
		} else {

			hUserName.put(userName, session);
		}
	}

	/**
	 * 
	 * * 用户被禁用时清空session(实际上username是percode)
	 */
	public synchronized static void removUser(String userName) {

		if (hUserName.containsKey(userName)) {
			HttpSession vsession = (HttpSession) hUserName.get(userName);
			try {
				vsession.invalidate();
				hUserName.remove(userName);
			} catch (Exception ex) {
			}
		}
	}

}

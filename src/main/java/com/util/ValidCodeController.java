package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证码
 * @author <a href="mailto:supingping@ssreader.cn">supingping</a>
 * @version 2015-5-27
 */
 
public class ValidCodeController {

	public static Logger logger = Logger.getLogger(ValidCodeController.class);

	 

	//@Get("getImg2")
	//@HttpFeatures(charset = "utf-8", contentType = "image/jpeg")
	public void getImg2(HttpServletResponse response,HttpServletRequest request) {
		try {
		 

		 
			// 生成随机字串
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			request.getSession().setMaxInactiveInterval(30 * 60000);
			/*request.getSession().setAttribute(key, verifyCode);*/

			// 生成图片
			int w = 123, h = 39;
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		}
		catch (Exception e) {
			logger.debug("生成验证码出错-->ValidCodeController lineImg error：" + e.getMessage());
		}
	}
}

package com.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.base.controller.RoleController;

/**
 * 
 * Title:GlobalDefaultExceptionHandler
 * Description:全局异常解析案例(在类上添加注释@ControllerAdvice,方法上添加注释@ExceptionHandler(value = Exception.class))
 * Company:chaoxing
 * @author:高鹤
 * @date:2017年6月30日
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public  String  defaultErrorException(HttpServletRequest request,Exception e){
		System.err.println("出错了");
		logger.error(e.getMessage());
		return e.getMessage();
	}
		
}
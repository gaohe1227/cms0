package com.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.StartApplication;
import com.cms.base.dao.CmsLogRepository;
import com.cms.base.mapper.UserMapper;
import com.cms.base.model.User;
import com.cms.base.service.RoleService;
import com.cms.base.service.UserService;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 			作用:登陆处理
 *
 */
@Controller 
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserMapper userMapper;
	 
	@Autowired
	private RoleService roleService;

	@RequestMapping("/login")
	public String login() throws Exception{ 
		logger.info("准备登陆");
		  return "login"; 
 
	}
 
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(HttpServletRequest request, User user,ModelMap  model) {
          System.out.println(user.getUserpwd()+"------------"+user.getUsercode());
		try {
			 user=this.userMapper.findUser(user);
			 
			 if(user==null){
				 /*request.setAttribute("errorMsg", );*/
				 model.addAttribute("errorMsg", "用户名或密码错误");
				 return "login";
			 } 
			  request.getSession().setAttribute("loginuser", user); 
			System.out.println("暂行登陆"); 

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		 
		}
		return "main";
		 
	}
	 
}

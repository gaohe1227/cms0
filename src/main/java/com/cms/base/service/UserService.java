package com.cms.base.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.base.dao.UserRepository;
import com.cms.base.mapper.UserMapper;
import com.cms.base.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:
 *
 */
@Service
@Transactional
public class UserService  {
	@Autowired
	private UserRepository userDao;//jpa
	@Autowired
	private UserMapper userMapper;//mybatis
 
	public User findUser(User user) {
		// TODO Auto-generated method stub
		return  userMapper.findUser(user);
	}
	 /*"*
     * 编辑用户
     */
	public Integer edit(User user) {
		// TODO Auto-generated method stub
		if(user.getUserid()==null){
			return   userDao.save(user).getUserid().intValue();
		}
		return userMapper.update(user);
	}

	public List<User> list(String userName, Integer currentPage) {
		// TODO Auto-generated method stub
		/*if(StringUtils.isNotBlank(userName)){
			return userDao.findAll(userName);
		}*/
		if(StringUtils.isNotBlank(userName)){
			userName="%"+userName.trim()+"%";
			return userMapper.findAllByUserName(userName);
		} 
		return userMapper.findAll();
	}

	public Integer deleteById(Long userid) {
		// TODO Auto-generated method stub
		return userMapper.deleteById(userid);
	}
}

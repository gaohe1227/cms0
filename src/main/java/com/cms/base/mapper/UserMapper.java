package com.cms.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.Query;

import com.cms.base.model.User;

public interface  UserMapper {
	 @Update("update t_cms_user set username=#{username},usercode=#{usercode},userpwd=#{userpwd} where userid =#{userid}")
	Integer update(User user);
	 @Update("update t_cms_user set status=1   where userid =#{userid}")
	Integer deleteById(Long userid);
	 @Select("select * from t_cms_user where username like #{username}")
	List<User> findAllByUserName(String userName);
	@Select("select * from t_cms_user")
	List<User> findAll();
	@Select("select userid,username,usercode from t_cms_user where usercode=#{usercode} and userpwd=#{userpwd}")
	User findUser(User user); 
	  
	 
}

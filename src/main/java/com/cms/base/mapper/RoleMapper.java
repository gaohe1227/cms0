package com.cms.base.mapper;

import org.apache.ibatis.annotations.Update;

import com.cms.base.model.Role;

public interface RoleMapper {

	@Update("update t_cms_role set   status=1 where id=#{id}")  
	Integer deleteById(Integer id);
}

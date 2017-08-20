package com.cms.base.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.base.dao.RoleRepository;
import com.cms.base.mapper.RoleMapper;
import com.cms.base.model.Role;

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
public class RoleService {
	 @Autowired
    private RoleRepository roleRepository;
	 @Autowired
	 private RoleMapper roleMapper;
    /*"*
     * 获取角色列表
     */
	public List<Role> list(String roleName) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(roleName)){
			return roleRepository.findAll(roleName);
		}
		return roleRepository.findAll();
	}
	 /*"*
     * 编辑角色
     */
	public Integer edit(Role role) {
		// TODO Auto-generated method stub
		if(role.getId()==null){
			return   roleRepository.save(role).getId();
		}
		return roleRepository.saveAndFlush(role).getId();
	}
 
	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub 
		return this.roleMapper.deleteById(id);
	 
	}
	public Role selectById(Integer id) {
		// TODO Auto-generated method stub
		return roleRepository.findOne(id);
	}
}

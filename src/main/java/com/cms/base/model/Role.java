package com.cms.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 			作用:角色实体类
 *
 */
@Entity
@Table(name = "t_cms_role")
public class Role {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "role_name")
	private String roleName;
	 
	private Integer status=0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	 

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

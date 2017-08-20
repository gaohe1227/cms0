package com.cms.base.model;

import javax.annotation.Generated;
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
 * 作用:
 *
 */
@Entity
@Table(name="t_cms_user")
public class User {
	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue
	private Long userid;

	/**
	 * 手机
	 */
	private String usertel;

	/**
	 * 电话
	 */
	private String usermobiletel;

	/**
	 * 用户编码
	 */
	@Column(name="usercode")
	private String usercode;

	/**
	 * 用户名称
	 */
	@Column(name="username")
	private String username;

	/**
	 * 登录密码
	 */
	private String userpwd;

	/**
	 * 用户所属部门
	 */
	private String userdep;

	/**
	 * 用户状态
	 */
	private String userstate;

	@Column(name="status")
	private int status;
 

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUsermobiletel() {
		return usermobiletel;
	}

	public void setUsermobiletel(String usermobiletel) {
		this.usermobiletel = usermobiletel;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserdep() {
		return userdep;
	}

	public void setUserdep(String userdep) {
		this.userdep = userdep;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	 
 

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
 

	 
}

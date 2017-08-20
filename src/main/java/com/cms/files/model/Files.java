package com.cms.files.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_cms_files")
public class Files implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private Long id;
	// 文件名称;
	private String name;
	// 发文时间;
	private Date datetime;
	private String datetimeString;
	// 操作员ID;
	private String userid;
	// 文件类型;
	private String type;
	// 文件原名;
	private String origname;
	// 文件路径;
	private String path;
	// 转换后的文件路径;
	private String swfpath;
	// 文件描述;
	private String discription;
	// 文件状态;
	private int state;

	public String getSwfpath() {
		return swfpath;
	}

	public void setSwfpath(String swfpath) {
		this.swfpath = swfpath;
	}

	public Files() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrigname() {
		return origname;
	}

	public void setOrigname(String origname) {
		this.origname = origname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getDatetimeString() {
		return datetimeString;
	}

	public void setDatetimeString(String datetimeString) {
		this.datetimeString = datetimeString;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
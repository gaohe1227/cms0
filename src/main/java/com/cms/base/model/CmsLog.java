package com.cms.base.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 日志 Description:
 * 
 * @author:高鹤
 * @date:2017年8月15日
 */
@Entity
@Table(name = "t_cms_log")
public class CmsLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String uri;
	private String ip;
	private Long userid;

	private Date start;
	@Transient // 该字段不被持久化
	private String startString;
	private Date end;
	@Transient
	private String endString;
	private String message;// 说明
	private Integer status = 0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	 

	public CmsLog( String uri, String ip, Long userid, Date start,  Date end,
			  String message, Integer status) {
		super(); 
		this.uri = uri;
		this.ip = ip;
		this.userid = userid;
		this.start = start; 
		this.end = end; 
		this.message = message;
		this.status = status;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
		if (start != null)
			this.startString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
		if (end != null)
			this.endString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CmsLog() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStartString() {
		return startString;
	}

	public void setStartString(String startString) {
		this.startString = startString;
	}

	public String getEndString() {
		return endString;
	}

	public void setEndString(String endString) {
		this.endString = endString;
	}

}
